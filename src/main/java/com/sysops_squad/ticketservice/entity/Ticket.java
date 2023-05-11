package com.sysops_squad.ticketservice.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long productId;
    private Timestamp creationTimestamp;

    private String concern;

    private String place;

    private Timestamp scheduledTimestamp;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(Long id, Long customerId, Long productId, Timestamp creationTimestamp, Timestamp scheduledTimestamp, String concern, String place, TicketStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.creationTimestamp = creationTimestamp;
        this.scheduledTimestamp = scheduledTimestamp;
        this.concern = concern;
        this.place = place;
        this.status = status;
    }

    public Ticket(Long customerId, Long productId, Timestamp creationTimestamp, Timestamp scheduledTimestamp, String concern, String place, TicketStatus status) {
        this.customerId = customerId;
        this.productId = productId;
        this.creationTimestamp = creationTimestamp;
        this.scheduledTimestamp = scheduledTimestamp;
        this.concern = concern;
        this.place = place;
        this.status = status;
    }

    public Long id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(customerId, ticket.customerId) && Objects.equals(productId, ticket.productId) && Objects.equals(creationTimestamp, ticket.creationTimestamp) && Objects.equals(concern, ticket.concern) && Objects.equals(place, ticket.place) && Objects.equals(scheduledTimestamp, ticket.scheduledTimestamp) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, productId, creationTimestamp, concern, place, scheduledTimestamp, status);
    }
}
