package com.example.ticketservice.request;

import com.example.ticketservice.entity.Ticket;
import com.example.ticketservice.entity.TicketStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.sql.Timestamp;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private final Long customerId;
    private final String concern;
    private final String timestamp;
    private final String place;

    public TicketCreated(Long customerId, String concern, String timestamp, String place) {
        this.customerId = customerId;
        this.concern = concern;
        this.timestamp = timestamp;
        this.place = place;
    }

    public Ticket toTicketEntity(TicketStatus ticketStatus) {
        return new com.example.ticketservice.entity.Ticket(customerId,
                Timestamp.valueOf(timestamp), concern, place, ticketStatus);
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

    public String place() {
        return place;
    }
}
