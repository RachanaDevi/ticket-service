package com.example.ticketservice.event;

import org.junit.jupiter.api.Test;

import static com.example.ticketservice.event.TicketStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class TicketStatusUnitTest {

    @Test
    void shouldReturnTotalTicketStatuses() {

        assertThat(TicketStatus.values()).containsExactly(CREATED, ASSIGNED, COMPLETED);
    }
}