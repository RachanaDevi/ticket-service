package com.example.ticketservice.event;

import com.example.ticketservice.model.Ticket;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketUnitTest {

    @Test
    void shouldEquateTwoRaisedTickets() {
        Ticket raisedTicket = new Ticket("anyCustomerId", "anyConcern", "anyDate");
        Ticket otherRaiseTicket = new Ticket("anyCustomerId", "anyConcern", "anyDate");

        assertThat(raisedTicket).isEqualTo(otherRaiseTicket);
    }

    @Test
    void shouldNotEquateTwoRaisedTicketsIfAnyFieldIsDifferent() {
        Ticket raisedTicket = new Ticket("anyCustomerId", "anyConcern", "anyDate");
        Ticket otherRaisedTicket = new Ticket("anyOtherCustomerId", "anyConcern", "anyDate");

        assertThat(raisedTicket).isNotEqualTo(otherRaisedTicket);
    }

}