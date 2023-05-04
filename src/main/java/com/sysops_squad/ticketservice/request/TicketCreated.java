package com.sysops_squad.ticketservice.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sysops_squad.ticketservice.entity.Ticket;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {

    public Ticket toEntity() {
        return new Ticket();
    }
}
