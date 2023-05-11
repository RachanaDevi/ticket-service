package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.request.TicketCreated;

public class TicketCreatedFixture {

    public static class Request {

        public static TicketCreated anyTicketCreated() {
            return new TicketCreated(1L, 1L, 1L, "Washing machine not working", "2023-02-20 01:24:00", "Pune");
        }
    }

    public static class Event {

        public static com.sysops_squad.ticketservice.event.TicketCreated anyTicketCreated() {
            return anyTicketCreatedWithId(1L);
        }

        public static com.sysops_squad.ticketservice.event.TicketCreated anyTicketCreatedWithId(Long id) {
            return new com.sysops_squad.ticketservice.event.TicketCreated(id, 1L, 1L,
                    "Washing machine not working",
                    "2023-02-20 01:24:00", "Pune");
        }
    }

}
