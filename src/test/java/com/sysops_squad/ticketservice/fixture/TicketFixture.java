package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;

import java.sql.Timestamp;

public class TicketFixture {


    public static Ticket anyTicketEntity() {
        return anyTicketEntityWithId(1L);
    }

    public static Ticket anyTicketEntityWithId(Long id) {
        return new Ticket(id, 1L, 1L, Timestamp.valueOf("2023-02-18 01:24:00"), Timestamp.valueOf("2023-02-20 01:24:00"),
                "Washing machine not working", "Pune", TicketStatus.CREATED);
    }

    public static Ticket anyTicketEntityWith(Long id, TicketStatus ticketStatus) {
        return new Ticket(id, 1L, 1L, Timestamp.valueOf("2023-02-18 01:24:00"), Timestamp.valueOf("2023-02-20 01:24:00"),
                "Washing machine not working", "Pune",ticketStatus);
    }
}
