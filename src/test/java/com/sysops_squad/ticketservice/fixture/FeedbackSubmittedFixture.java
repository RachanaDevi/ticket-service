package com.sysops_squad.ticketservice.fixture;

import com.sysops_squad.ticketservice.request.FeedbackSubmitted;

public class FeedbackSubmittedFixture {

    public static class Request {

        public static FeedbackSubmitted anyFeedbackSubmittedWithTicketId(Long ticketId) {
            return new FeedbackSubmitted(ticketId, "Good service", 10);
        }
        public static FeedbackSubmitted anyFeedbackSubmitted() {
            return new FeedbackSubmitted(1L, "Good service", 10);
        }
    }
}
