package com.example.ticketservice.producer;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketCreated;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static com.example.ticketservice.fixture.TicketCreatedFixture.anyTicket;
import static com.example.ticketservice.fixture.TicketCreatedFixture.anyTicketCreated;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TicketCreatedPublisherTest {

    @Test
    void shouldPublishAnEventWithGivenTopic() {
        KafkaTemplate<String, Ticket> kafkaTemplate = mock(KafkaTemplate.class);

        TicketPublisher ticketPublisher = new TicketPublisher(kafkaTemplate);
        ticketPublisher.publish(anyTicket());

        ArgumentCaptor<String> topicArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(kafkaTemplate).send(topicArgumentCaptor.capture(), any());

        assertThat(topicArgumentCaptor.getValue()).isEqualTo("ticket-service-topic");
    }

    @Test
    void shouldPublishEventWithGivenTicketEvent() {
        KafkaTemplate<String, Ticket> kafkaTemplate = mock(KafkaTemplate.class);

        TicketPublisher producer = new TicketPublisher(kafkaTemplate);
        producer.publish(anyTicket());

        ArgumentCaptor<Ticket> messageArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);
        verify(kafkaTemplate).send(any(), messageArgumentCaptor.capture());

        assertThat(messageArgumentCaptor.getValue()).isEqualTo(anyTicket());
    }
}