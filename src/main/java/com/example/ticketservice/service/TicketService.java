package com.example.ticketservice.service;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketStatus;
import com.example.ticketservice.producer.TicketPublisher;
import com.example.ticketservice.repository.TicketRepository;
import com.example.ticketservice.request.TicketCreated;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private TicketPublisher ticketPublisher;

    private TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketPublisher ticketPublisher, TicketRepository ticketRepository) {
        this.ticketPublisher = ticketPublisher;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public void saveAndPublish(TicketCreated ticketCreated) {
        com.example.ticketservice.entity.Ticket ticketEntity = ticketCreated.toTicketEntity(TicketStatus.CREATED);
        ticketRepository.save(ticketEntity);
        ticketPublisher.publish(Ticket.from(ticketCreated, ticketEntity.id()));
    }

}
