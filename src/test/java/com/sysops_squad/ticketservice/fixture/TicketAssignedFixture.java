package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.entity.TicketAssigned;

public class TicketAssignedFixture {

    public static class Entity {

        public static TicketAssigned anyTicketAssigned() {
            return new TicketAssigned(1L, 1L);
        }
    }

    public static class Event {


        public static com.sysops_squad.ticketservice.event.TicketAssigned anyTicketAssigned() {
            return anyTicketAssignedWithTicketId(1L);
        }

        public static com.sysops_squad.ticketservice.event.TicketAssigned anyTicketAssignedWithTicketId(Long ticketId) {
            return new com.sysops_squad.ticketservice.event.TicketAssigned(ticketId, 1L);
        }
    }
}
