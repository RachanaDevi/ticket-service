package com.example.ticketservice.entity;


import com.example.ticketservice.event.TicketStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    private UUID id;

    private Long customerId;

    private Timestamp timestamp;

    private String concern;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(Long customerId, Timestamp timestamp, String concern, TicketStatus status) {
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.concern = concern;
        this.status = status;
    }

    public Ticket(UUID id, Long customerId, Timestamp timestamp, String concern, TicketStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.concern = concern;
        this.status = status;
    }
}
