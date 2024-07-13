package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.helper.UserHelpers;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest // Sets up application context for text in realistic env
@ActiveProfiles("test") // Specifies running with test profile (config)
public class UserServiceIT {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelpers userHelpers;

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceIT.class);

    @Test
    public void testGetAllUsersFailed() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getAllUsers();
        });

        assertEquals("No users in database", exception.getMessage());
    }

}
