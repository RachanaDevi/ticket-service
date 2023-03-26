package com.example.ticketservice.service;

import com.example.ticketservice.constants.KafkaConfigConstants;
import com.example.ticketservice.entity.Feedback;
import com.example.ticketservice.entity.TicketStatus;
import com.example.ticketservice.event.TicketAssigned;
import com.example.ticketservice.producer.TicketPublisher;
import com.example.ticketservice.repository.FeedbackRepository;
import com.example.ticketservice.repository.TicketAssignedRepository;
import com.example.ticketservice.repository.TicketRepository;
import com.example.ticketservice.request.FeedbackSubmitted;
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

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public TicketService(TicketPublisher ticketPublisher,
                         TicketRepository ticketRepository,
                         TicketAssignedRepository ticketAssignedRepository,
                         FeedbackRepository feedbackRepository) {
        this.ticketPublisher = ticketPublisher;
        this.ticketRepository = ticketRepository;
        this.ticketAssignedRepository = ticketAssignedRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public void saveAndPublish(com.example.ticketservice.request.TicketCreated ticketCreated) {
        com.example.ticketservice.entity.Ticket ticketEntity = ticketCreated.toTicketEntity(TicketStatus.CREATED);
        ticketRepository.save(ticketEntity);
        ticketPublisher.publish(ticketCreated.toTicketCreatedWithId(ticketEntity.id()));
    }

    @KafkaListener(topics = KafkaConfigConstants.TICKET_ASSIGNED_TOPIC,
            groupId = KafkaConfigConstants.TICKET_EVENT_CONSUMER_GROUP
    )
    public void updateTicketStatusAndAssignTicket(TicketAssigned ticketAssignedEvent) {
        ticketAssignedRepository.save(com.example.ticketservice.entity.TicketAssigned.from(ticketAssignedEvent));
        Optional<com.example.ticketservice.entity.Ticket> ticketAssigned = ticketRepository.findById(ticketAssignedEvent.ticketId());
        ticketAssigned.ifPresent(ticket -> ticketRepository.updateTicketStatus(ticket.id(), TicketStatus.ASSIGNED));
    }

    public void updateTicketStatusAsCompletedAndAddFeedback(FeedbackSubmitted feedbackSubmitted) {
        Feedback feedback = feedbackRepository.save(feedbackSubmitted.toFeedback());
        ticketRepository.updateTicketStatus(feedback.ticketId(), TicketStatus.COMPLETED);
    }
}
