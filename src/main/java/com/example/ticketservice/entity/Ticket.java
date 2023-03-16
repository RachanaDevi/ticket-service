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

    private Timestamp timestamp;

    private String concern;

    private String place;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Long id() {
        return id;
    }

    public Ticket() {
    }

    public Ticket(Long customerId, Timestamp timestamp, String concern, String place, TicketStatus status) {
        this.customerId = customerId;
        this.timestamp = timestamp;
        this.concern = concern;
        this.place = place;
        this.status = status;
    }
}
