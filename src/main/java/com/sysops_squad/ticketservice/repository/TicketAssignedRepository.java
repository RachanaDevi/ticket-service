package com.sysops_squad.ticketservice.repository;

import com.sysops_squad.ticketservice.entity.TicketAssigned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketAssignedRepository extends JpaRepository<TicketAssigned, Long> {
}