package com.example.ticketservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String place;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(long id, String name, String place, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.phoneNumber = phoneNumber;
    }
}
