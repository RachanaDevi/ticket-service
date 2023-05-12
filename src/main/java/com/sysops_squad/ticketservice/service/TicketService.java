package com.sysops_squad.ticketservice.service;

import com.sysops_squad.ticketservice.constants.KafkaConstants;
import com.sysops_squad.ticketservice.entity.Ticket;
import com.sysops_squad.ticketservice.entity.TicketStatus;
import com.sysops_squad.ticketservice.event.TicketAssigned;
import com.sysops_squad.ticketservice.producer.TicketPublisher;
import com.sysops_squad.ticketservice.repository.TicketAssignedRepository;
import com.sysops_squad.ticketservice.repository.TicketRepository;
import com.sysops_squad.ticketservice.request.TicketCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private TicketPublisher ticketPublisher;

    private TicketAssignedRepository ticketAssignedRepository;

    public TicketService(TicketRepository ticketRepository, TicketPublisher ticketPublisher, TicketAssignedRepository ticketAssignedRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketPublisher = ticketPublisher;
        this.ticketAssignedRepository = ticketAssignedRepository;
    }

    public void saveAndPublish(TicketCreated ticketCreated) {
        Ticket createdTicket = ticketRepository.save(ticketCreated.toEntity(TicketStatus.CREATED));
        ticketPublisher.publish(ticketCreated.toTicketCreatedWithId(createdTicket.id()));
    }

    @KafkaListener(topics = KafkaConstants.TICKET_ASSIGNED_TOPIC, groupId = KafkaConstants.TICKET_EVENT_CONSUMER_GROUP)
    public void updateTicketStatusAndAssignTicket(TicketAssigned ticketAssignedEvent) {
        ticketAssignedRepository.save(com.sysops_squad.ticketservice.entity.TicketAssigned.from(ticketAssignedEvent));
        Optional<Ticket> ticketAssigned = ticketRepository.findById(ticketAssignedEvent.ticketId());
        ticketAssigned.ifPresent(ticket -> ticketRepository.updateTicketStatus(ticket.id(), TicketStatus.ASSIGNED));
    }
}
