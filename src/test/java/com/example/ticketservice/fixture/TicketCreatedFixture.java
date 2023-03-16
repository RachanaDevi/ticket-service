package com.example.ticketservice.fixture;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketCreated;

public class TicketCreatedFixture {

    public static Ticket anyTicket() {
        return new Ticket(1L, 2l, "anyConcern", "anyDate", "anyPlace");
    }


    public static Ticket anyOtherTicket() {
        return new Ticket(1L, 3l, "anyConcern", "anyDate", "anyPlace");
    }
    public static TicketCreated anyTicketCreated() {
        return new TicketCreated(anyTicket());
    }

}
