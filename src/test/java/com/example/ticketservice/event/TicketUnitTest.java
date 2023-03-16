package com.example.ticketservice.event;

import com.example.ticketservice.event.Ticket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketUnitTest {

    @Test
    void shouldEquateTwoRaisedTickets() {
        Ticket raisedTicket = new Ticket(1L, 2L, "anyConcern", "anyDate", "anyPlace");
        Ticket otherRaiseTicket = new Ticket(1L,2L, "anyConcern", "anyDate", "anyPlace");

        assertThat(raisedTicket).isEqualTo(otherRaiseTicket);
    }

    @Test
    void shouldNotEquateTwoRaisedTicketsIfAnyFieldIsDifferent() {
        Ticket raisedTicket = new Ticket(1L, 3L, "anyConcern", "anyDate", "anyPlace");
        Ticket otherRaisedTicket = new Ticket(1L, 2L, "anyConcern", "anyDate", "anyPlace");

        assertThat(raisedTicket).isNotEqualTo(otherRaisedTicket);
    }

}