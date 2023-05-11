package com.sysops_squad.ticketservice.event;

import java.util.Objects;

public class TicketCreated {
    private final Long id;

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
