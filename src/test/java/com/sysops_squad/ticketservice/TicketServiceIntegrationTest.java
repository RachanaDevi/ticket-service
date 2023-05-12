package com.sysops_squad.ticketservice;

import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;
import com.sysops_squad.ticketservice.event.TicketAssigned;
import com.sysops_squad.ticketservice.event.TicketCreated;
import com.sysops_squad.ticketservice.fixture.TicketAssignedFixture;
import com.sysops_squad.ticketservice.fixture.TicketCreatedFixture;
import com.sysops_squad.ticketservice.fixture.TicketFixture;
import com.sysops_squad.ticketservice.repository.TicketAssignedRepository;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import com.sysops_squad.ticketservice.service.TicketService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sysops_squad.ticketservice.constants.KafkaConstants.TICKET_ASSIGNED_TOPIC;
import static com.sysops_squad.ticketservice.constants.KafkaConstants.TICKET_CREATED_TOPIC;
import static com.sysops_squad.ticketservice.fixture.TicketFixture.anyTicketEntityWithId;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration-test")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TicketServiceApplication.class)
public class TicketServiceIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private static KafkaConsumer<String, TicketCreated> kafkaConsumer;

    private static KafkaProducer<String, TicketAssigned> kafkaProducer;

    @Container
    public static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("postgres");
    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketAssignedRepository ticketAssignedRepository;

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.consumer.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("spring.kafka.producer.bootstrap-servers", kafkaContainer::getBootstrapServers);
    }

    @BeforeTestClass
    public void beforeTest() {
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(
                messageListenerContainer -> {
                    ContainerTestUtils.waitForAssignment(messageListenerContainer, 1);
                }
        );
    }

    @BeforeEach
    public void setup() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(kafkaContainer.getBootstrapServers(), "test-group", "true");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        consumerProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        kafkaConsumer = new KafkaConsumer<>(consumerProps);
        kafkaConsumer.subscribe(Collections.singleton(TICKET_CREATED_TOPIC));
        kafkaProducer = new KafkaProducer<>(producerConfigProperties());
        ticketRepository.deleteAll();
        ticketAssignedRepository.deleteAll();
    }

    static {
        kafkaContainer.start();
    }

    @AfterEach
    void tearDown() {
        ticketAssignedRepository.deleteAll();
        ticketRepository.deleteAll();
    }

    @AfterAll
    static void tearDownAfterAll() {
        kafkaContainer.stop();
    }

    @Test
    void shouldReturnProductsHavingAGivenProductCategoryId() {
        ResponseEntity<String> response = testRestTemplate.exchange(urlForEndpoint(Endpoints.createTicket)
                , HttpMethod.POST, httpEntityForTicketCreated(), new ParameterizedTypeReference<>() {
                });

        ConsumerRecords<String, TicketCreated> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(20));
        ConsumerRecord<String, TicketCreated> consumerRecord = consumerRecords.iterator().next();

        Assertions.assertAll(
                () -> assertThat(consumerRecords.count()).isOne(),
                () -> assertThat(consumerRecord.value()).isEqualTo(TicketCreatedFixture.Event.anyTicketCreatedWithId(1L)),
                () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED),
                () -> assertThat(ticketRepository.findAll()).usingRecursiveComparison().ignoringFields("creationTimestamp").isEqualTo(List.of(anyTicketEntityWithId(1L)))
        );
    }

    @Test
    void shouldUpdateTicketStatusAsTicketAssigned() throws InterruptedException {
        Ticket ticket = ticketRepository.save(TicketFixture.anyTicketEntity());

        ProducerRecord<String, TicketAssigned> record = new ProducerRecord<>(TICKET_ASSIGNED_TOPIC, TicketAssignedFixture.Event.anyTicketAssignedWithTicketId(ticket.id()));
        kafkaProducer.send(record);

        TimeUnit.SECONDS.sleep(5);

        long ticketAssignedId = 1L;
        Assertions.assertAll(
                () -> assertThat(ticketRepository.findById(ticket.id()).get()).isEqualTo(TicketFixture.anyTicketEntityWith(ticket.id(), TicketStatus.ASSIGNED)),
                () -> assertThat(ticketAssignedRepository.findById(ticketAssignedId)).isPresent());
    }

    @NotNull
    private HttpEntity<com.sysops_squad.ticketservice.request.TicketCreated> httpEntityForTicketCreated() {
        return new HttpEntity<>(TicketCreatedFixture.Request.anyTicketCreated(), httpHeaders());
    }

    @NotNull
    private HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private String urlForEndpoint(String endpoint) {
        return "http://localhost:" + port + endpoint;
    }

    @NotNull
    private Map<String, Object> producerConfigProperties() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    public static class Endpoints {
        static String createTicket = "/createTicket";
    }
}
