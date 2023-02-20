package com.example.ticketservice.event;

import com.example.ticketservice.event.Ticket;
import org.junit.jupiter.api.Test;

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
        Ticket raisedTicket = new Ticket("anyCustomerId", "anyConcern", "anyDate");
        TicketCreated ticketCreated = TicketCreated.createdFrom(raisedTicket);

        assertThat(ticketCreated.status()).isEqualTo(TicketStatus.CREATED);
    }

    @Test
    void shouldGetTicketEventFromRaisedTicket() {
        Ticket raisedTicket = new Ticket("anyCustomerId", "anyConcern", "anyDate");
        TicketCreated ticketCreated = TicketCreated.createdFrom(raisedTicket);

        TicketCreated otherTicketCreated = new TicketCreated(anyTicket());

        assertThat(ticketCreated).isEqualTo(otherTicketCreated);
    }
}