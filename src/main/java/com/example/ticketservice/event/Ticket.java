package com.example.ticketservice.event;

import com.example.ticketservice.request.TicketCreated;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private final Long ticketId;
    private final String concern;
    private final String scheduledTimestamp;
    private final String place;

    @JsonCreator
    public Ticket(Long ticketId, String concern, String scheduledTimestamp, String place) {
        this.concern = concern;
        this.scheduledTimestamp = scheduledTimestamp;
        this.place = place;
        this.ticketId = ticketId;
    }

    public static Ticket from(TicketCreated ticketCreated, Long id) {
        return new Ticket(id, ticketCreated.concern(), ticketCreated.scheduledTimestamp(), ticketCreated.place());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(concern, ticket.concern) && Objects.equals(scheduledTimestamp, ticket.scheduledTimestamp) && Objects.equals(place, ticket.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, concern, scheduledTimestamp, place);
    }
}
