package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.request.TicketCreated;

public class TicketCreatedFixture {

    public static TicketCreated anyTicketCreated() {
        return new TicketCreated();
    }
}
