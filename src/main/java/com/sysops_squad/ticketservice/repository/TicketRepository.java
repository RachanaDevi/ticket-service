package com.sysops_squad.ticketservice.repository;

import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Ticket SET status = :ticketStatus WHERE id = :id")
    @Transactional
    void updateTicketStatus(Long id, TicketStatus ticketStatus);
}
