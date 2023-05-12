package com.sysops_squad.ticketservice.request;

import com.sysops_squad.ticketservice.fixture.FeedbackFixture;
import com.sysops_squad.ticketservice.fixture.FeedbackSubmittedFixture;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FeedbackSubmittedTest {

    @Test
    void shouldReturnFeedback() {
        FeedbackSubmitted feedbackSubmitted = FeedbackSubmittedFixture.Request.anyFeedbackSubmitted();

        assertThat(feedbackSubmitted.toFeedback()).isEqualTo(FeedbackFixture.anyFeedback());
    }
}