package com.sysops_squad.ticketservice.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TicketCreated {
    private Long id;

    public TicketCreated() {
    }

    public TicketCreated(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketCreated that = (TicketCreated) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
