package com.example.ticketservice.event;

import org.junit.jupiter.api.Test;

import static com.example.ticketservice.fixture.TicketFixture.anyOtherTicket;
import static com.example.ticketservice.fixture.TicketFixture.anyTicket;
import static org.assertj.core.api.Assertions.assertThat;

class TicketUnitTest {

    @Test
    void shouldEquateTwoRaisedTickets() {
        Ticket raisedTicket = anyTicket();
        Ticket otherRaiseTicket = anyTicket();

        assertThat(raisedTicket).isEqualTo(otherRaiseTicket);
    }

    @Test
    void shouldNotEquateTwoRaisedTicketsIfAnyFieldIsDifferent() {
        Ticket raisedTicket = anyTicket();
        Ticket otherRaisedTicket = anyOtherTicket();

        assertThat(raisedTicket).isNotEqualTo(otherRaisedTicket);
    }

}