package com.example.ticketservice.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketId;

    @Range(min = 0, max = 10)
    private Integer rating;

    private String text;

    public Feedback() {
    }

    public Feedback(Long ticketId, Integer rating, String text) {
        this.ticketId = ticketId;
        this.rating = rating;
        this.text = text;
    }

    public Long id() {
        return id;
    }

    public Long ticketId() {
        return ticketId;
    }

    //    @OneToOne
//    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
//    private Ticket ticket;
}
