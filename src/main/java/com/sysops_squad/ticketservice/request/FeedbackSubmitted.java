package com.sysops_squad.ticketservice.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sysops_squad.ticketservice.entity.Feedback;

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

    @Override
    public String toString() {
        return "FeedbackSubmitted{" +
                "ticketId=" + ticketId +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
