package com.sysops_squad.ticketservice.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

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

    public Feedback(Long id, Long ticketId, Integer rating, String text) {
        this.id = id;
        this.ticketId = ticketId;
        this.rating = rating;
        this.text = text;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(id, feedback.id) && Objects.equals(ticketId, feedback.ticketId) && Objects.equals(rating, feedback.rating) && Objects.equals(text, feedback.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketId, rating, text);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                '}';
    }
}
