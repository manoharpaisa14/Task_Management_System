package com.task_management.operations_tm.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private static final String url = "http://localhost:8080/task"; // Replace with your task endpoint
    private RestTemplate restTemplate;
    
    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testPostMethodWithBasicAuth() {
        // Create the task object to send
    	 StringBuilder taskJBuilder = new StringBuilder();
         taskJBuilder.append("{");
         taskJBuilder.append("\"title\": \"New Task\", ");
         taskJBuilder.append("\"description\": \"Task description\", ");
         taskJBuilder.append("\"due_date\": \"2024-12-31T00:00:00\", ");
         taskJBuilder.append("\"status\": \"PENDING\"");
         taskJBuilder.append("}");

         String taskJ = taskJBuilder.toString(); 
        // Set up Basic Authentication
        String username = "admin";
        String password = "admin123";
        String auth = "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", auth); // Set the Basic Auth header
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type to JSON
        
        HttpEntity<String> entity = new HttpEntity<>(taskJ, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Assert response status
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Expected HTTP Status 201");
        assertEquals("Task added", response.getBody(), "Expected response body to be 'Task added'");
    }
}
