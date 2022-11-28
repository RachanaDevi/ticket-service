package com.example.ticketservice.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RaisedTicketUnitTest {

    @Test
    void shouldEquateTwoRaisedTickets() {
        RaisedTicket raisedTicket = new RaisedTicket("anyCustomerId", "anyConcern", "anyDate");
        RaisedTicket otherRaisedTicket = new RaisedTicket("anyCustomerId", "anyConcern", "anyDate");

        assertThat(raisedTicket).isEqualTo(otherRaisedTicket);
    }

    @Test
    void shouldNotEquateTwoRaisedTicketsIfAnyFieldIsDifferent() {
        RaisedTicket raisedTicket = new RaisedTicket("anyCustomerId", "anyConcern", "anyDate");
        RaisedTicket otherRaisedTicket = new RaisedTicket("anyOtherCustomerId", "anyConcern", "anyDate");

        assertThat(raisedTicket).isNotEqualTo(otherRaisedTicket);
    }

}