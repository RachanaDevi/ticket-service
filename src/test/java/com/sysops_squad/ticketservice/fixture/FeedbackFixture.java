package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.entity.Feedback;

public class FeedbackFixture {


    public static Feedback anyFeedbackWithTicketId(Long ticketId) {
        return new Feedback(1L, ticketId, 10, "Good service");
    }


    public static Feedback anyFeedback() {
        return new Feedback(1L, 10, "Good service");
    }

    public static Feedback anyFeedbackWithFeedbackId() {
        return anyFeedbackWithTicketId(1L);
    }
}
