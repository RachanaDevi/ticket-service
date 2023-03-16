package com.example.ticketservice.event;

import com.example.ticketservice.event.Ticket;
import org.junit.jupiter.api.Test;

import static com.example.ticketservice.fixture.TicketCreatedFixture.anyOtherTicket;
import static com.example.ticketservice.fixture.TicketCreatedFixture.anyTicket;
import static org.assertj.core.api.Assertions.assertThat;

class TicketCreatedUnitTest {

    @Test
    void shouldEquateTwoTicketEvents() {
        TicketCreated raisedTicket = new TicketCreated(anyTicket());
        TicketCreated otherTicketCreated = new TicketCreated(anyTicket());

        assertThat(raisedTicket).isEqualTo(otherTicketCreated);
    }

    @Test
    void shouldNotEquateTwoTicketEventsIfAnyFieldIsDifferent() {
        TicketCreated raisedTicket = new TicketCreated(anyTicket());
        TicketCreated otherTicketCreated = new TicketCreated(anyOtherTicket());

        assertThat(raisedTicket).isNotEqualTo(otherTicketCreated);
    }
}