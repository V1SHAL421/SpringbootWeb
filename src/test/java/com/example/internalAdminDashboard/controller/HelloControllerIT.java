package com.example.internalAdminDashboard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

// The embedded server starts on a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// IT stands for integration test
public class HelloControllerIT {

    @Autowired
    private TestRestTemplate template;
    // Actual port is configured automatically in base URL for TestRestTemplate

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response =
                template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Hello everyone!");
    }

    @Test
    public void getWelcome() throws Exception {
        ResponseEntity<String> response =
                template.getForEntity("/welcome", String.class);
        assertThat(response.getBody()).isEqualTo("Welcome to my website");
    }
}
