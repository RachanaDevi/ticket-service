package com.sysops_squad.ticketservice.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tickets_assigned")
public class TicketAssigned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketId;

    private Long consultantId;

    public TicketAssigned() {
    }

    public TicketAssigned(Long ticketId, Long consultantId) {
        this.ticketId = ticketId;
        this.consultantId = consultantId;
    }

    public static TicketAssigned from(com.sysops_squad.ticketservice.event.TicketAssigned ticketAssignedEvent) {
        return new TicketAssigned(ticketAssignedEvent.ticketId(), ticketAssignedEvent.consultantId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketAssigned that = (TicketAssigned) o;
        return Objects.equals(id, that.id) && Objects.equals(ticketId, that.ticketId) && Objects.equals(consultantId, that.consultantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketId, consultantId);
    }
}