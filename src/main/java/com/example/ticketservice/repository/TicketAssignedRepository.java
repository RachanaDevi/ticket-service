package com.example.ticketservice.repository;

import com.example.ticketservice.entity.TicketAssigned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketAssignedRepository extends JpaRepository<TicketAssigned, Long> {
}
