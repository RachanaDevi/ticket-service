package com.sysops_squad.ticketservice;

import com.sysops_squad.ticketservice.event.TicketCreated;
import com.sysops_squad.ticketservice.fixture.TicketCreatedFixture;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import com.sysops_squad.ticketservice.service.TicketService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.*;
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
        kafkaConsumer.subscribe(Collections.singleton("ticket-created-topic"));
    }

    static {
        kafkaContainer.start();
    }

    @AfterEach
    void tearDown() {
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


    @TestConfiguration
    static class KafkaTestContainersConfiguration {
        @Bean
        public ProducerFactory<String, TicketCreated> testProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean
        public KafkaTemplate<String, TicketCreated> kafkaTemplate() {
            return new KafkaTemplate<>(testProducerFactory());
        }
    }

    public static class Endpoints {
        static String createTicket = "/createTicket";
    }
}
