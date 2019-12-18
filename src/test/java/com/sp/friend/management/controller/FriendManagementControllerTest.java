package com.sp.friend.management.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FriendManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_when_validFriendConnectionRequest_thenIsOk() throws Exception {
        String json = "{\"friends\":[\"andy@example.com\",\"john@example.com\"]}";
        mockMvc.perform(post("/api/friends").content(json).contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    void test_when_RuntimeException_thenExceptionHandlingResponse() throws Exception {
        String json = "{\"friends\":[\"andy@example.com\",\"john@example.com\"]}";
        mockMvc.perform(post("/api/friends").content(json).contentType("application/json")).andExpect(status().is5xxServerError());
    }



}