package com.example.ticketservice.entity;

import com.example.ticketservice.entity.TicketStatus;
import org.junit.jupiter.api.Test;

import static com.example.ticketservice.entity.TicketStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class TicketStatusUnitTest {

    @Test
    void shouldReturnTotalTicketStatuses() {

        assertThat(TicketStatus.values()).containsExactly(CREATED, ASSIGNED, COMPLETED);
    }
}