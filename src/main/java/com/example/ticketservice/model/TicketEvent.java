package com.example.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketEvent {

    private String customerId;
    private String concern;
    private String date;
    private TicketStatus ticketStatus;

    @JsonCreator
    public TicketEvent(String customerId, String concern, String date, TicketStatus ticketStatus) {
        this.customerId = customerId;
        this.concern = concern;
        this.date = date;
        this.ticketStatus = ticketStatus;
    }

    public static TicketEvent createdFrom(RaisedTicket raisedTicket) {
        return new TicketEvent(raisedTicket.customerId(), raisedTicket.concern(), raisedTicket.date(), TicketStatus.CREATED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEvent that = (TicketEvent) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(concern, that.concern) && Objects.equals(date, that.date) && ticketStatus == that.ticketStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, concern, date, ticketStatus);
    }


    public TicketStatus status() {
        return this.ticketStatus;
    }
}
