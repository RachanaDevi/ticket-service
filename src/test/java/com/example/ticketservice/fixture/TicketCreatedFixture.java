package com.example.ticketservice.fixture;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketCreated;

public class TicketCreatedFixture {

    public static Ticket anyTicket() {
        return new Ticket(1L, "anyConcern", "anyDate");
    }

    public static TicketCreated anyTicketCreated() {
        return new TicketCreated(anyTicket());
    }

}
