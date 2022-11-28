package com.example.ticketservice.model;

import java.time.LocalDate;
import java.util.Objects;

public class RaisedTicket {

    private String customerId;
    private String concern;
    private String date;

    public RaisedTicket(String customerId, String concern, String date) {
        this.customerId = customerId;
        this.concern = concern;
        this.date = date;
    }

    String customerId() {
        return customerId;
    }

    String concern() {
        return concern;
    }

    String date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaisedTicket that = (RaisedTicket) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(concern, that.concern) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, concern, date);
    }
}
