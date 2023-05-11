package com.sysops_squad.ticketservice.producer;

import com.sysops_squad.ticketservice.event.TicketCreated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class TicketPublisher {

    private final KafkaTemplate<String, TicketCreated> kafkaTemplate;

    public TicketPublisher(KafkaTemplate<String, TicketCreated> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<SendResult<String, TicketCreated>> publish(TicketCreated ticketCreated) {
        return kafkaTemplate.send("ticket-created-topic", ticketCreated);
    }
}
