package com.example.ticketservice.event;

import com.example.ticketservice.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

class TicketUnitTest {

    @Test
    void shouldEquateTwoRaisedTickets() {
        Ticket raisedTicket = new Ticket(1L, "anyConcern", Timestamp.valueOf("2023-02-18 01:24:00"));
        Ticket otherRaiseTicket = new Ticket(1L, "anyConcern", Timestamp.valueOf("2023-02-18 01:24:00"));

        assertThat(raisedTicket).isEqualTo(otherRaiseTicket);
    }

    @Test
    void shouldNotEquateTwoRaisedTicketsIfAnyFieldIsDifferent() {
        Ticket raisedTicket = new Ticket(1L, "anyConcern", Timestamp.valueOf("2023-02-18 01:24:00"));
        Ticket otherRaisedTicket = new Ticket(2L, "anyConcern", Timestamp.valueOf("2023-03-18 12:00:00"));

        assertThat(raisedTicket).isNotEqualTo(otherRaisedTicket);
    }

}