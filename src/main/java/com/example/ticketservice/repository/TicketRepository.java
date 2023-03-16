package com.example.ticketservice.repository;

import com.example.ticketservice.entity.Ticket;
import com.example.ticketservice.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Modifying
    @Query(value = "UPDATE Ticket SET status = :ticketStatus WHERE id = :id")
    @Transactional
    void updateTicketStatus(Long id, TicketStatus ticketStatus);
}
