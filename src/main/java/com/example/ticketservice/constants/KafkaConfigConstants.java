package com.example.ticketservice.constants;

public class KafkaConfigConstants {

    public static final String TICKET_EVENT_CONSUMER_GROUP = "message-consumer-group";
    public static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static final String TICKET_ASSIGNED_TOPIC = "ticket-assigned-topic";
    public static final String TICKET_CREATED_TOPIC = "ticket-created-topic";
}
