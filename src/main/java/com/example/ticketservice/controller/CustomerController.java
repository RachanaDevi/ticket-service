package com.example.ticketservice.controller;


import com.example.ticketservice.event.TicketCreated;
import com.example.ticketservice.model.Ticket;
import com.example.ticketservice.producer.TicketPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    @Autowired
    private TicketPublisher ticketPublisher;

    @PostMapping(value = "/createTicket", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> createTicket(@RequestBody Ticket raisedTicket) {
        ticketPublisher.publish(TicketCreated.createdFrom(raisedTicket));
        return ResponseEntity.ok("Ticket submitted successfully!");
    }
}
