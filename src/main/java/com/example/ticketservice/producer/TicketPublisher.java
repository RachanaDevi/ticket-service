package com.example.ticketservice.producer;

import com.example.ticketservice.event.TicketCreated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.ticketservice.constants.KafkaConfigConstants.TICKET_SERVICE_TOPIC;

@Component
public class TicketPublisher {

    private final KafkaTemplate<String, TicketCreated> kafkaTemplate;

    public TicketPublisher(KafkaTemplate<String, TicketCreated> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, TicketCreated>> publish(TicketCreated ticketEvent) {
        return kafkaTemplate.send(TICKET_SERVICE_TOPIC, ticketEvent);
    }
}