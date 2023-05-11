package com.sysops_squad.ticketservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Ticket() {
    }

    public Ticket(Long id) {
        this.id = id;
    }

    public Long id() {
        return this.id;
    }
}
