package com.example.ticketservice.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ticket {

    private final String customerId;
    private final String concern;
    private final String date;

    @JsonCreator
    public Ticket(String customerId, String concern, String date) {
        this.customerId = customerId;
        this.concern = concern;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(customerId, ticket.customerId) && Objects.equals(concern, ticket.concern) && Objects.equals(date, ticket.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, concern, date);
    }
}
