package com.sysops_squad.ticketservice.request;

import com.sysops_squad.ticketservice.fixture.TicketCreatedFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class TicketCreatedTest {


//    private MockedStatic<Clock> clockMock;

    @BeforeEach
    void setUp() {
//        Clock spyClock = spy(Clock.class);
//        clockMock = mockStatic(Clock.class);
//        clockMock.when(Clock::systemUTC).thenReturn(spyClock);
//        when(spyClock.instant()).thenReturn(Instant.parse("2023-02-18T12:44:14.287783Z"));
    }

    @Test
    void shouldReturnTicketEntity() {
        try (MockedStatic<Instant> mockedInstant = Mockito.mockStatic(Instant.class)) {
            Instant fixedInstant = Instant.parse("2023-02-18T12:44:14.287783Z");
            mockedInstant.when(Instant::now).thenReturn(fixedInstant);
            assertThat(Instant.now()).isEqualTo(Instant.parse("2023-02-18T12:44:14.287783Z"));

            TicketCreated ticketCreated = TicketCreatedFixture.Request.anyTicketCreated();
//            assertThat(ticketCreated.toEntity(TicketStatus.CREATED)).isEqualTo(TicketFixture.anyTicketEntity());
        }
//
    }
}