package com.example.ticketservice.request;

import com.example.ticketservice.entity.Ticket;
import com.example.ticketservice.entity.TicketStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.sql.Timestamp;
import java.time.Instant;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    private static final Timestamp TICKET_CREATED_TIMESTAMP = Timestamp.from(Instant.now());
    private final Long customerId;
    private final String concern;
    private final String scheduledTimestamp;
    private final String place;

    public TicketCreated(Long customerId, String concern, String scheduledTimestamp, String place) {
        this.customerId = customerId;
        this.concern = concern;
        this.scheduledTimestamp = scheduledTimestamp;
        this.place = place;
    }

    public Ticket toTicketEntity(TicketStatus ticketStatus) {
        return new com.example.ticketservice.entity.Ticket(customerId,
                TICKET_CREATED_TIMESTAMP, Timestamp.valueOf(scheduledTimestamp), concern, place, ticketStatus);
    }

    public Long customerId() {
        return customerId;
    }

    public String concern() {
        return concern;
    }

    public String scheduledTimestamp() {
        return scheduledTimestamp;
    }

    public String place() {
        return place;
    }
}
