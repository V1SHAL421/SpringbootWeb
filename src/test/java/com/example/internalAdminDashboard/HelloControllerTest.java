package com.example.internalAdminDashboard;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
/*
Injected a Mockmvc instance using the two annotations above. The HTTP request cycle has been mocked.
 */
public class HelloControllerTest {
    @Autowired // Used for dependency injection
    // Autowired automatically injects dependencies into Spring-managed beans
    // Spring resolves object dependency by matching it with a bean in the Spring container
    // Bean is then injected into class where Autowired is defined
    // Autowired reduces need for getters and setters/constructor code to assign values to props as Spring handles it all.
    // Autowired promotes decoupling/ loose coupling through classes not knowing about instantiation and location of dependencies
    // Since dependencies injected by Spring, easier to replace them with mocks (more testable)
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello everyone!")));
    }

    @Test
    public void getWelcome() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to my website")));
    }
}
