package com.example.ticketservice.service;

import com.example.ticketservice.entity.Ticket;
import com.example.ticketservice.producer.TicketPublisher;
import com.example.ticketservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    private TicketPublisher ticketPublisher;


    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketPublisher ticketPublisher) {
        this.ticketRepository = ticketRepository;
        this.ticketPublisher = ticketPublisher;
    }

//    public void saveAndPublish(Ticket ticket){
//        ticketRepository.save(ticket)
//
//    }
}
