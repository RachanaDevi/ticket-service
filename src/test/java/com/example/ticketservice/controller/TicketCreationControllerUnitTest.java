package com.example.ticketservice.controller;

import com.example.ticketservice.event.Ticket;
import com.example.ticketservice.producer.TicketPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TicketCreationController.class)
class TicketCreationControllerUnitTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketPublisher producer;

    @Test
    void shouldReturnResponseAsOK() throws Exception {
        Ticket raisedTicket = new Ticket(1L, 2L, "anyConcern", "anyDate", "anyPlace");
        var payload = new ObjectMapper().writeValueAsString(raisedTicket);

        MockHttpServletResponse response = mockMvc.perform(post("/createTicket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(MockHttpServletResponse.SC_OK);
    }

    @Test
    void shouldReturnResponseBody() throws Exception {
        Ticket raisedTicket = new Ticket(1L, 2L, "anyConcern", "anyDate", "anyPlace");
        var payload = new ObjectMapper().writeValueAsString(raisedTicket);

        MockHttpServletResponse response = mockMvc.perform(post("/createTicket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEqualTo("Ticket submitted successfully!");
    }
}