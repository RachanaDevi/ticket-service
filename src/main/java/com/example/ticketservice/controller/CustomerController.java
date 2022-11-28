package com.example.ticketservice.controller;


import com.example.ticketservice.model.RaisedTicket;
import com.example.ticketservice.model.TicketEvent;
import com.example.ticketservice.producer.TicketEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    @Autowired
    private TicketEventPublisher ticketEventPublisher;

    @PostMapping(value = "/createTicket", consumes = "application/json")
    @ResponseBody
    public ResponseEntity createTicket(@RequestBody RaisedTicket raisedTicket) {
        ticketEventPublisher.publish(TicketEvent.createdFrom(raisedTicket));
        return ResponseEntity.ok("Ticket submitted successfully!");
    }
}
