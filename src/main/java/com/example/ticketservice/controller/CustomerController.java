package com.example.ticketservice.controller;


import com.example.ticketservice.request.TicketCreated;
import com.example.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    @Autowired
    private TicketService ticketService;

    @PostMapping(value = "/createTicket", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> createTicket(@RequestBody TicketCreated ticketCreated) {
        ticketService.saveAndPublish(ticketCreated);
        return ResponseEntity.ok("Ticket submitted successfully!");
    }
}
