package com.sysops_squad.ticketservice.controller;

import com.sysops_squad.ticketservice.controller.request.TicketCreated;
import com.sysops_squad.ticketservice.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(value = "/createTicket", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> createTicket(@RequestBody TicketCreated ticketCreated) {
        ticketService.saveAndPublish(ticketCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ticket submitted successfully!");
    }
}
