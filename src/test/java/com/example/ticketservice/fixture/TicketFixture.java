package com.example.ticketservice.fixture;

import com.example.ticketservice.event.Ticket;

public class TicketFixture {

    public static Ticket anyTicket() {
        return new Ticket(1L, 2l, "anyConcern", "anyDate", "anyPlace");
    }


    public static Ticket anyOtherTicket() {
        return new Ticket(1L, 3l, "anyConcern", "anyDate", "anyPlace");
    }
}
