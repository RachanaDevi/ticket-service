package com.example.ticketservice.fixture;

import com.example.ticketservice.event.TicketCreated;

public class TicketFixture {

    public static TicketCreated anyTicket() {
        return new TicketCreated(1L, "anyConcern", "anyDate", "anyPlace");
    }


    public static TicketCreated anyOtherTicket() {
        return new TicketCreated(1L, "anyConcern", "anyDate", "anyPlace");
    }
}
