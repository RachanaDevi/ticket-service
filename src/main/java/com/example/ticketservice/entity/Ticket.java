package com.example.ticketservice.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Timestamp creationTimestamp;

    private String concern;

    private String place;

    private Timestamp scheduledTimestamp;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Long id() {
        return id;
    }

    public Ticket() {
    }

    public Ticket(Long customerId, Timestamp creationTimestamp, Timestamp scheduledTimestamp, String concern, String place, TicketStatus status) {
        this.customerId = customerId;
        this.creationTimestamp = creationTimestamp;
        this.scheduledTimestamp = scheduledTimestamp;
        this.concern = concern;
        this.place = place;
        this.status = status;
    }
}
