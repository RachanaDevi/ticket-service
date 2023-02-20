package com.example.ticketservice.service;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketStatus;
import com.example.ticketservice.producer.TicketPublisher;
import com.example.ticketservice.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
    public void saveAndPublish(Ticket ticket) {
        ticketPublisher.publish(ticket);
        ticketRepository.save(new com.example.ticketservice.entity.Ticket(ticket.customerId(),
                Timestamp.valueOf(ticket.timestamp()), ticket.concern(), TicketStatus.CREATED));
//        ticketRepository.saveCreatedTicket(ticket.customerId(), ticket.concern(), Timestamp.valueOf(ticket.timestamp()));
    }

}
