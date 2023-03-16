package com.example.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketAssigned {

    private Long ticketId;
    private Long consultantId;

    public TicketAssigned() {
    }

    public TicketAssigned(Long ticketId, Long consultantId) {
        this.ticketId = ticketId;
        this.consultantId = consultantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketAssigned that = (TicketAssigned) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(consultantId, that.consultantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, consultantId);
    }

    public Long consultantId() {
        return consultantId;
    }

    public Long ticketId() {
        return ticketId;
    }
}
