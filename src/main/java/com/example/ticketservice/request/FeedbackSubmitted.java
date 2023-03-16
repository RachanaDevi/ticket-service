package com.example.ticketservice.request;

import com.example.ticketservice.entity.Feedback;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FeedbackSubmitted {
    private Long ticketId;
    private String text;
    private Integer rating;

    public FeedbackSubmitted(Long ticketId, String text, Integer rating) {
        this.ticketId = ticketId;
        this.text = text;
        this.rating = rating;
    }

    public Feedback toFeedback() {
        return new Feedback(ticketId, rating, text);
    }
}
