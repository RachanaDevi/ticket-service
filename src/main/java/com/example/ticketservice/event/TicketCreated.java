package com.example.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private final Ticket ticket;
    private final TicketStatus ticketStatus;

    @JsonCreator
    public TicketCreated(Ticket ticket) {
        this.ticket = ticket;
        this.ticketStatus = TicketStatus.CREATED;
    }

    public static TicketCreated createdFrom(Ticket raisedTicket) {
        return new TicketCreated(raisedTicket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreated that = (TicketCreated) o;
        return Objects.equals(ticket, that.ticket) && ticketStatus == that.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket, ticketStatus);
    }

    public TicketStatus status() {
        return this.ticketStatus;
    }
}
