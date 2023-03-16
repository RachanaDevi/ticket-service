package com.example.ticketservice.event;

import com.example.ticketservice.request.TicketCreated;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private final Long ticketId;
    private final Long customerId;
    private final String concern;
    private final String timestamp;

    private final String place;

    @JsonCreator
    public Ticket(Long ticketId, Long customerId, String concern, String timestamp, String place) {
        this.customerId = customerId;
        this.concern = concern;
        this.timestamp = timestamp;
        this.place = place;
        this.ticketId = ticketId;
    }

    public static Ticket from(TicketCreated ticketCreation, Long id) {
        return new Ticket(id, ticketCreation.customerId(), ticketCreation.concern(), ticketCreation.timestamp(), ticketCreation.place());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(customerId, ticket.customerId) && Objects.equals(concern, ticket.concern) && Objects.equals(timestamp, ticket.timestamp) && Objects.equals(place, ticket.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, customerId, concern, timestamp, place);
    }
}
