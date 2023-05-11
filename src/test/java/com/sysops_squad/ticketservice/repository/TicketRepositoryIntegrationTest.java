package com.sysops_squad.ticketservice.repository;

import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;
import com.sysops_squad.ticketservice.fixture.TicketFixture;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class TicketRepositoryIntegrationTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.clear();
        ticketRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        ticketRepository.deleteAll();
        entityManager.flush();
    }

    @Test
    @Transactional
    void shouldUpdateTicketStatusToTheGivenTicketStatus() {
        long ticketId = 1L;
        ticketRepository.save(TicketFixture.anyTicketEntityWith(1L, TicketStatus.CREATED));

        ticketRepository.updateTicketStatus(ticketId, TicketStatus.ASSIGNED);

        Optional<Ticket> ticket = ticketRepository.findById(ticketId);

        assertThat(ticket.get()).isEqualTo(TicketFixture.anyTicketEntityWith(ticketId, TicketStatus.ASSIGNED));
    }
}