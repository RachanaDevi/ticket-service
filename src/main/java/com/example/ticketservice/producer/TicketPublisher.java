package com.example.ticketservice.producer;

import com.example.ticketservice.event.Ticket;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

import static com.example.ticketservice.constants.KafkaConfigConstants.TICKET_CREATED_TOPIC;

@Component
public class TicketPublisher {

    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    public TicketPublisher(KafkaTemplate<String, Ticket> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, Ticket>> publish(Ticket ticket) {
        return kafkaTemplate.send(TICKET_CREATED_TOPIC, ticket);
    }
}