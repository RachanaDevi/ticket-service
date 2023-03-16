package com.example.ticketservice.event;

import com.example.ticketservice.event.Ticket;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private final Ticket ticket;

    @JsonCreator
    public TicketCreated(Ticket ticket) {
        this.ticket = ticket;
    }

    public static TicketCreated createdFrom(Ticket raisedTicket) {
        return new TicketCreated(raisedTicket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreated that = (TicketCreated) o;
        return Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }

}
