package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.entity.Ticket;

public class TicketFixture {


    public static Ticket anyTicketEntity() {
        return anyTicketEntityWithId(1L);
    }

    public static Ticket anyTicketEntityWithId(Long id) {
        return new Ticket(id);
    }
}
