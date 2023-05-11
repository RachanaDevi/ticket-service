package com.sysops_squad.ticketservice.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;

import java.sql.Timestamp;
import java.time.Instant;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

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

    public Ticket toEntity(TicketStatus ticketStatus) {
        return new Ticket(customerId,
                productId, Timestamp.from(Instant.now()), Timestamp.valueOf(scheduledTimestamp), concern, place, ticketStatus);
    }
}
