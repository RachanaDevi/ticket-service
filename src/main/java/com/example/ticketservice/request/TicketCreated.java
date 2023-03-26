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
    private final Long productId;
    private final Long productCategoryId;
    private final String concern;
    private final String scheduledTimestamp;
    private final String place;

    public TicketCreated(Long customerId, Long productId, Long productCategoryId, String concern, String scheduledTimestamp, String place) {
        this.customerId = customerId;
        this.productId = productId;
        this.productCategoryId = productCategoryId;
        this.concern = concern;
        this.scheduledTimestamp = scheduledTimestamp;
        this.place = place;
    }

    public Ticket toTicketEntity(TicketStatus ticketStatus) {
        return new com.example.ticketservice.entity.Ticket(customerId,
                productId, TICKET_CREATED_TIMESTAMP, Timestamp.valueOf(scheduledTimestamp), concern, place, ticketStatus);
    }

    public com.example.ticketservice.event.TicketCreated toTicketCreatedWithId(Long id) {
        return new com.example.ticketservice.event.TicketCreated(id, productId, productCategoryId, concern, scheduledTimestamp, place);
    }
}
