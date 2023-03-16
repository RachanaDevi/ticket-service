package com.example.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private final UUID ticketId;
    private final Long customerId;
    private final String concern;
    private final String timestamp;

    @JsonCreator
    public Ticket(Long customerId, String concern, String timestamp) {
        this.ticketId = UUID.randomUUID();
        this.customerId = customerId;
        this.concern = concern;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(customerId, ticket.customerId) && Objects.equals(concern, ticket.concern) && Objects.equals(timestamp, ticket.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, customerId, concern, timestamp);
    }

    public Long customerId() {
        return customerId;
    }

    public String concern() {
        return concern;
    }

    public String timestamp() {
        return timestamp;
    }

    public UUID ticketId() {
        return ticketId;
    }
}
