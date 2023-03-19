package com.example.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private final Long ticketId;
    private final String concern;
    private final String scheduledTimestamp;
    private final String place;

    @JsonCreator
    public TicketCreated(Long ticketId, String concern, String scheduledTimestamp, String place) {
        this.concern = concern;
        this.scheduledTimestamp = scheduledTimestamp;
        this.place = place;
        this.ticketId = ticketId;
    }

    public static TicketCreated from(com.example.ticketservice.request.TicketCreated ticketCreated, Long id) {
        return new TicketCreated(id, ticketCreated.concern(), ticketCreated.scheduledTimestamp(), ticketCreated.place());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreated ticketCreated = (TicketCreated) o;
        return Objects.equals(ticketId, ticketCreated.ticketId) && Objects.equals(concern, ticketCreated.concern) && Objects.equals(scheduledTimestamp, ticketCreated.scheduledTimestamp) && Objects.equals(place, ticketCreated.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, concern, scheduledTimestamp, place);
    }
}
