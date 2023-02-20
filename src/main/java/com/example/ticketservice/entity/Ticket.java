package com.example.ticketservice.entity;

import com.example.ticketservice.event.TicketStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tickets")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    private String concern;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp timestamp;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(Long customerId, String concern, Timestamp timestamp) {
        this.customerId = customerId;
        this.concern = concern;
        this.timestamp = timestamp;
    }

    public Ticket(TicketStatus status) {
        this.status = status;
    }

    @JsonCreator
    public Ticket(Long id, Long customerId, String concern, Timestamp timestamp, TicketStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.concern = concern;
        this.timestamp = timestamp;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(customerId, ticket.customerId) && Objects.equals(concern, ticket.concern) && Objects.equals(timestamp, ticket.timestamp) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, concern, timestamp, status);
    }
}
