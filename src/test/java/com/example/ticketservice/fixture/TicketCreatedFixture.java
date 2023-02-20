package com.example.ticketservice.fixture;

import com.example.ticketservice.entity.Ticket;
import com.example.ticketservice.event.TicketCreated;

import java.sql.Timestamp;

public class TicketCreatedFixture {

    public static Ticket anyTicket() {
        return  new Ticket(1L, "anyConcern", Timestamp.valueOf("2023-02-18 01:24:00"));
    }

    public static TicketCreated anyTicketCreated() {
        return new TicketCreated(anyTicket());
    }

}
