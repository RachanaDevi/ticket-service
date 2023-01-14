package com.example.ticketservice.model;

import org.junit.jupiter.api.Test;

import static com.example.ticketservice.model.TicketStatus.CREATED;
import static org.assertj.core.api.Assertions.assertThat;

class TicketStatusUnitTest {

    @Test
    void shouldReturnTotalTicketStatuses() {

        assertThat(TicketStatus.values()).containsExactly(CREATED);
    }
}