package com.example.ticketservice.event;

import com.example.ticketservice.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

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
        TicketCreated otherTicketCreated = new TicketCreated(anyTicket());

        assertThat(raisedTicket).isNotEqualTo(otherTicketCreated);
    }

    @Test
    void shouldGetTicketStatusAsCreatedFromRaisedTicket() {
        Ticket raisedTicket = anyTicket();
        TicketCreated ticketCreated = TicketCreated.createdFrom(raisedTicket);

        assertThat(ticketCreated.status()).isEqualTo(TicketStatus.CREATED);
    }

    private Ticket anyTicket() {
        return  new Ticket(1L, "anyConcern", Timestamp.valueOf("2023-02-18 01:24:00"));
    }

    @Test
    void shouldGetTicketEventFromRaisedTicket() {
        Ticket raisedTicket = anyTicket();
        TicketCreated ticketCreated = TicketCreated.createdFrom(raisedTicket);

        TicketCreated otherTicketCreated = new TicketCreated(anyTicket());

        assertThat(ticketCreated).isEqualTo(otherTicketCreated);
    }
}