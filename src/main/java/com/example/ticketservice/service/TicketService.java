package com.example.ticketservice.service;

import com.example.ticketservice.constants.KafkaConfigConstants;
import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.event.TicketAssigned;
import com.example.ticketservice.event.TicketStatus;
import com.example.ticketservice.producer.TicketPublisher;
import com.example.ticketservice.repository.TicketAssignedRepository;
import com.example.ticketservice.repository.TicketRepository;
import com.example.ticketservice.request.TicketCreated;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private final TicketPublisher ticketPublisher;

    private final TicketRepository ticketRepository;

    private final TicketAssignedRepository ticketAssignedRepository;

    @Autowired
    public TicketService(TicketPublisher ticketPublisher, TicketRepository ticketRepository, TicketAssignedRepository ticketAssignedRepository) {
        this.ticketPublisher = ticketPublisher;
        this.ticketRepository = ticketRepository;
        this.ticketAssignedRepository = ticketAssignedRepository;
    }

    @Transactional
    public void saveAndPublish(TicketCreated ticketCreated) {
        com.example.ticketservice.entity.Ticket ticketEntity = ticketCreated.toTicketEntity(TicketStatus.CREATED);
        ticketRepository.save(ticketEntity);
        ticketPublisher.publish(Ticket.from(ticketCreated, ticketEntity.id()));
    }

    @KafkaListener(topics = KafkaConfigConstants.TICKET_ASSIGNED_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public void updateTicketStatusAndAssignTicket(TicketAssigned ticketAssignedEvent) {
        ticketAssignedRepository.save(com.example.ticketservice.entity.TicketAssigned.from(ticketAssignedEvent));
        Optional<com.example.ticketservice.entity.Ticket> ticketAssigned = ticketRepository.findById(ticketAssignedEvent.ticketId());
        ticketAssigned.ifPresent(ticket -> ticketRepository.updateTicketStatus(ticket.id(), TicketStatus.ASSIGNED));
    }
}
