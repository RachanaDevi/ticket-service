package com.example.ticketservice.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketEventUnitTest {

    @Test
    void shouldEquateTwoTicketEvents() {
        TicketEvent raisedTicket = new TicketEvent("anyCustomerId", "anyConcern", "anyDate", TicketStatus.CREATED);
        TicketEvent otherTicketEvent = new TicketEvent("anyCustomerId", "anyConcern", "anyDate", TicketStatus.CREATED);

        assertThat(raisedTicket).isEqualTo(otherTicketEvent);
    }

    @Test
    void shouldNotEquateTwoTicketEventsIfAnyFieldIsDifferent() {
        TicketEvent raisedTicket = new TicketEvent("anyCustomerId", "anyConcern", "anyDate", TicketStatus.CREATED);
        TicketEvent otherTicketEvent = new TicketEvent("anyOtherCustomerId", "anyConcern", "anyDate", TicketStatus.CREATED);

        assertThat(raisedTicket).isNotEqualTo(otherTicketEvent);
    }

    @Test
    void shouldGetTicketStatusAsCreatedFromRaisedTicket() {
        RaisedTicket raisedTicket = new RaisedTicket("anyCustomerId", "anyConcern", "anyDate");
        TicketEvent ticketEvent = TicketEvent.createdFrom(raisedTicket);

        assertThat(ticketEvent.status()).isEqualTo(TicketStatus.CREATED);
    }

    @Test
    void shouldGetTicketEventFromRaisedTicket() {
        RaisedTicket raisedTicket = new RaisedTicket("anyCustomerId", "anyConcern", "anyDate");
        TicketEvent ticketEvent = TicketEvent.createdFrom(raisedTicket);

        TicketEvent otherTicketEvent = new TicketEvent("anyCustomerId", "anyConcern", "anyDate", TicketStatus.CREATED);

        assertThat(ticketEvent).isEqualTo(otherTicketEvent);
    }
}