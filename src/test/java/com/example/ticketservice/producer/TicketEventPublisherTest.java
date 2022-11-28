package com.example.ticketservice.producer;

import com.example.ticketservice.model.TicketEvent;
import com.example.ticketservice.model.TicketStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TicketEventPublisherTest {

    @Test
    void shouldPublishAnEventWithGivenTopic() {
        KafkaTemplate<String, TicketEvent> kafkaTemplate = mock(KafkaTemplate.class);

        TicketEventPublisher ticketEventPublisher = new TicketEventPublisher(kafkaTemplate);
        ticketEventPublisher.publish(anyTicketEvent());

        ArgumentCaptor<String> topicArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate).send(topicArgumentCaptor.capture(), any());

        assertThat(topicArgumentCaptor.getValue()).isEqualTo("message-topic");
    }

    @Test
    void shouldPublishEventWithGivenTicketEvent() {
        KafkaTemplate<String, TicketEvent> kafkaTemplate = mock(KafkaTemplate.class);

        TicketEventPublisher producer = new TicketEventPublisher(kafkaTemplate);
        TicketEvent ticketEvent = anyTicketEvent();
        producer.publish(ticketEvent);

        ArgumentCaptor<TicketEvent> messageArgumentCaptor = ArgumentCaptor.forClass(TicketEvent.class);
        verify(kafkaTemplate).send(any(), messageArgumentCaptor.capture());

        assertThat(messageArgumentCaptor.getValue()).isEqualTo(ticketEvent);
    }

    private TicketEvent anyTicketEvent() {
        return new TicketEvent("anyCustomerId", "anyConcern", "anyDate", TicketStatus.CREATED);
    }
}