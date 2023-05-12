package com.sysops_squad.ticketservice.consumer;

import com.sysops_squad.ticketservice.event.TicketAssigned;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static com.sysops_squad.ticketservice.constants.KafkaConstants.TICKET_EVENT_CONSUMER_GROUP;

@EnableKafka
@Configuration
public class TicketAssignedConsumerConfig {


    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TicketAssigned> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TicketAssigned> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, TicketAssigned> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations());
    }

    @Bean
    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, TICKET_EVENT_CONSUMER_GROUP);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configurations.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configurations.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        configurations.put(JsonDeserializer.VALUE_DEFAULT_TYPE, TicketAssigned.class);

        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configurations.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        configurations.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        return configurations;
    }
}
