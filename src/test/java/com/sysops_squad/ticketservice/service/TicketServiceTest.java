package com.sysops_squad.ticketservice.service;

import com.sysops_squad.ticketservice.entity.TicketStatus;
import com.sysops_squad.ticketservice.event.TicketCreated;
import com.sysops_squad.ticketservice.fixture.TicketAssignedFixture;
import com.sysops_squad.ticketservice.fixture.TicketCreatedFixture;
import com.sysops_squad.ticketservice.fixture.TicketFixture;
import com.sysops_squad.ticketservice.producer.TicketPublisher;
import com.sysops_squad.ticketservice.repository.TicketAssignedRepository;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static com.sysops_squad.ticketservice.fixture.TicketCreatedFixture.Request.anyTicketCreated;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Test
    void shouldSaveTicketCreated() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.save(any())).thenReturn(TicketFixture.anyTicketEntity());

        TicketService ticketService = new TicketService(ticketRepository, mock(TicketPublisher.class), mock(TicketAssignedRepository.class));

        ticketService.saveAndPublish(anyTicketCreated());

        verify(ticketRepository).save(any());
    }

    @Test
    void shouldPublishTicketCreated() {
        Long ticketId = 1L;

        TicketPublisher ticketPublisher = mock(TicketPublisher.class);
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.save(any())).thenReturn(TicketFixture.anyTicketEntityWithId(ticketId));

        TicketService ticketService = new TicketService(ticketRepository, ticketPublisher, mock(TicketAssignedRepository.class));
        ticketService.saveAndPublish(anyTicketCreated());

        ArgumentCaptor<TicketCreated> ticketCreatedArgumentCaptor = ArgumentCaptor.forClass(TicketCreated.class);
        verify(ticketPublisher).publish(ticketCreatedArgumentCaptor.capture());

        assertThat(ticketCreatedArgumentCaptor.getValue()).isEqualTo(TicketCreatedFixture.Event.anyTicketCreatedWithId(ticketId));
    }

    @Test
    void shouldUpdateTicketStatusAsAssigned() {
        long ticketId = 1L;
        TicketRepository ticketRepository = mock(TicketRepository.class);
        when(ticketRepository.findById(any())).thenReturn(Optional.of(TicketFixture.anyTicketEntityWithId(ticketId)));

        TicketService ticketService = new TicketService(ticketRepository, mock(TicketPublisher.class), mock(TicketAssignedRepository.class));

        ticketService.updateTicketStatusAndAssignTicket(TicketAssignedFixture.Event.anyTicketAssignedWithTicketId(ticketId));

        ArgumentCaptor<TicketStatus> ticketStatusArgumentCaptor = ArgumentCaptor.forClass(TicketStatus.class);
        verify(ticketRepository).updateTicketStatus(any(), ticketStatusArgumentCaptor.capture());

        assertThat(ticketStatusArgumentCaptor.getValue()).isEqualTo(TicketStatus.ASSIGNED);
    }
}