package com.example.ticketservice.fixture;

import com.example.ticketservice.event.Ticket;

public class TicketFixture {

    public static Ticket anyTicket() {
        return new Ticket(1L, "anyConcern", "anyDate", "anyPlace");
    }


    public static Ticket anyOtherTicket() {
        return new Ticket(1L, "anyConcern", "anyDate", "anyPlace");
    }
}
