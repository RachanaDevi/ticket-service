package com.sysops_squad.ticketservice.entity;

import com.sysops_squad.ticketservice.fixture.TicketAssignedFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketAssignedTest {

    @Test
    void shouldReturnTicketAssignedFromTicketAssignedEvent() {
        TicketAssigned ticketAssigned = TicketAssigned.from(TicketAssignedFixture.Event.anyTicketAssigned());

        assertThat(ticketAssigned).isEqualTo(TicketAssignedFixture.Entity.anyTicketAssigned());
    }
}