package com.example.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private final Long ticketId;

    private final Long productId;

    private final Long productCategoryId;
    private final String concern;
    private final String scheduledTimestamp;
    private final String place;

    @JsonCreator
    public TicketCreated(Long ticketId, Long productId, Long productCategoryId, String concern, String scheduledTimestamp, String place) {
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.concern = concern;
        this.scheduledTimestamp = scheduledTimestamp;
        this.place = place;
        this.ticketId = ticketId;
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
