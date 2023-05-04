package com.sysops_squad.ticketservice.service;

import com.sysops_squad.ticketservice.fixture.TicketCreatedFixture;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TicketServiceTest {

    @Test
    void shouldSaveTicketCreated() {
        TicketRepository ticketRepository = mock(TicketRepository.class);
        TicketService ticketService = new TicketService(ticketRepository);

        ticketService.saveAndPublish(TicketCreatedFixture.anyTicketCreated());

        verify(ticketRepository).save(any());
    }
}