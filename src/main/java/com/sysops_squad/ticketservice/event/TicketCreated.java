package com.sysops_squad.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private Long ticketId;
    private Long productId;
    private Long productCategoryId;
    private String concern;
    private String scheduledTimestamp;
    private String place;

    public TicketCreated() {
    }

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
