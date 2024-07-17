package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.InternalAdminDashboardApplication;
import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Objects;

@ActiveProfiles("test")
@SpringBootTest(classes = InternalAdminDashboardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InfoControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        User user4 = new User("James", 23);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void getAllUsers() throws Exception {
        List<UserDTO> userDTOS = List.of(new UserDTO("Tim", 19), new UserDTO("Eric", 24), new UserDTO("Nick", 23), new UserDTO("James", 23));

        ResponseEntity<List<UserDTO>> response =
                template.exchange("/user/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {});

        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        assertThat(response.getBody()).size().isEqualTo(userDTOS.size());
        assertThat(response.getBody())
                .usingElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrderElementsOf(userDTOS);
    }

    @Test
    public void getUserFromName() throws Exception {
        UserDTO userDTO = new UserDTO("Tim", 19);

        ResponseEntity<UserDTO> response =
                template.exchange("/user/{name}", HttpMethod.GET, null, UserDTO.class, "Tim");

        assertNotNull(response.getBody());
        assertThat(Objects.equals(response.getBody().getName(), userDTO.getName()));
        assertThat(Objects.equals(response.getBody().getAge(), userDTO.getAge()));
    }

    @Test
    public void getUsersByAge() throws Exception {
        List<UserDTO> userDTOS = List.of(new UserDTO("Nick", 23), new UserDTO("James", 23));

        ResponseEntity<List<UserDTO>> response =
                template.exchange("/user/age/{age}", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {}, 23);

        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        assertThat(response.getBody()).size().isEqualTo(userDTOS.size());
        assertThat(response.getBody())
                .usingElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrderElementsOf(userDTOS);
    }

    }

