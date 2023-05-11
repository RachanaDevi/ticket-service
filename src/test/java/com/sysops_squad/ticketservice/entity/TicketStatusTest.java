package com.sysops_squad.ticketservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketStatusTest {

    @Test
    void shouldReturnAllTicketStatuses() {
        TicketStatus[] ticketStatuses = {TicketStatus.CREATED, TicketStatus.ASSIGNED, TicketStatus.COMPLETED};

        assertThat(TicketStatus.values()).containsExactly(ticketStatuses);
    }
}