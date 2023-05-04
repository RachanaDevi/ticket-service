package com.sysops_squad.ticketservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysops_squad.ticketservice.request.TicketCreated;
import com.sysops_squad.ticketservice.service.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TicketControllerTest {

    public static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    void shouldCreateTicket() throws Exception {
        String requestBody = MAPPER.writeValueAsString(new TicketCreated());
        mockMvc.perform(post("/createTicket")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(response -> assertThat(response.getResponse().getContentAsString()).isEqualTo("Ticket submitted successfully!"));
    }
}
