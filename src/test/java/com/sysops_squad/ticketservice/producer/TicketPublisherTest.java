package com.sysops_squad.ticketservice.producer;

import com.sysops_squad.ticketservice.event.TicketCreated;
import com.sysops_squad.ticketservice.fixture.TicketCreatedFixture;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicketPublisherTest {

    @Test
    void shouldPublishToTopic() {
        KafkaTemplate<String, TicketCreated> kafkaTemplate = mock(KafkaTemplate.class);
        ArgumentCaptor<String> topicArgumentCaptor = ArgumentCaptor.forClass(String.class);

        TicketPublisher ticketPublisher = new TicketPublisher(kafkaTemplate);
        ticketPublisher.publish(TicketCreatedFixture.Event.anyTicketCreated());

        verify(kafkaTemplate).send(topicArgumentCaptor.capture(), any());

        assertThat(topicArgumentCaptor.getValue()).isEqualTo("ticket-created-topic");
    }
}