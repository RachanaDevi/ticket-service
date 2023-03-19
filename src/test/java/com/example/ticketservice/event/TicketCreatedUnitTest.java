package com.example.ticketservice.event;

import org.junit.jupiter.api.Test;

import static com.example.ticketservice.fixture.TicketFixture.anyOtherTicket;
import static com.example.ticketservice.fixture.TicketFixture.anyTicket;
import static org.assertj.core.api.Assertions.assertThat;

class TicketCreatedUnitTest {

    @Test
    void shouldEquateTwoRaisedTickets() {
        TicketCreated raisedTicket = anyTicket();
        TicketCreated otherRaiseTicket = anyTicket();

        assertThat(raisedTicket).isEqualTo(otherRaiseTicket);
    }

    @Test
    void shouldNotEquateTwoRaisedTicketsIfAnyFieldIsDifferent() {
        TicketCreated raisedTicket = anyTicket();
        TicketCreated otherRaisedTicket = anyOtherTicket();

        assertThat(raisedTicket).isNotEqualTo(otherRaisedTicket);
    }

}