package com.sysops_squad.ticketservice.service;

import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;
import com.sysops_squad.ticketservice.producer.TicketPublisher;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import com.sysops_squad.ticketservice.request.TicketCreated;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private TicketPublisher ticketPublisher;

    public TicketService(TicketRepository ticketRepository, TicketPublisher ticketPublisher) {
        this.ticketRepository = ticketRepository;
        this.ticketPublisher = ticketPublisher;
    }

    public void saveAndPublish(TicketCreated ticketCreated) {
        Ticket createdTicket = ticketRepository.save(ticketCreated.toEntity(TicketStatus.CREATED));
        ticketPublisher.publish(new com.sysops_squad.ticketservice.event.TicketCreated(createdTicket.id()));
    }
}
