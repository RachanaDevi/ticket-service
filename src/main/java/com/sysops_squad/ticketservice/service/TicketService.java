package com.sysops_squad.ticketservice.service;

import com.sysops_squad.ticketservice.request.TicketCreated;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void saveAndPublish(TicketCreated ticketCreated) {
        ticketRepository.save(ticketCreated.toEntity());
    }
}
