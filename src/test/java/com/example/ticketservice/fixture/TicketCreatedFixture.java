package com.example.ticketservice.fixture;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketCreated;
import com.example.ticketservice.event.TicketStatus;

public class TicketCreatedFixture {

    public static Ticket anyTicket() {
        return new Ticket("anyCustomerId", "anyConcern", "anyDate");
    }

    public static TicketCreated anyTicketCreated() {
        return new TicketCreated(anyTicket());
    }

}
