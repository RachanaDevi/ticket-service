package com.example.ticketservice.repository;

import com.example.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


//    @Modifying
//    @Query(value = "INSERT INTO tickets(customer_id, concern, status, timestamp) VALUES (:customerId, :concern, 'CREATED', current_timestamp)", nativeQuery = true)
//    void saveCreatedTicket(String customerId, String concern, Timestamp timestamp);
}
