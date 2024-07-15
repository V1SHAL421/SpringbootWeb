package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.helper.UserHelpers;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest // Sets up application context for text in realistic env
@Transactional // Ensures that changes made during each test does not persist beyond test scope
// This is done through the transaction rolling back after each test method is completed
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

    @Test
    public void testGetOneUser() {
        User user = new User("Tim", 19);
        userRepository.save(user);
        List<UserDTO> userDTOS = userService.getAllUsers();

        assertNotNull(userDTOS);
        assertFalse(userDTOS.isEmpty());
        assertEquals(userDTOS.size(), 1);
        assertEquals("Tim", userDTOS.getFirst().getName());

    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User("Sara", 29);
        User user2 = new User("Tim", 19);

        userRepository.save(user1);
        userRepository.save(user2);
        List<UserDTO> userDTOS = userService.getAllUsers();

        assertNotNull(userDTOS);
        assertFalse(userDTOS.isEmpty());
        assertEquals(userDTOS.size(), 2);
        assertEquals("Sara", userDTOS.getFirst().getName());

    }

    @Test
    public void testGetUserByIdFailed() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });

        assertEquals("No users with this id", exception.getMessage());
    }

    @Test
    public void testGetUserById() {
        User user = new User("Tim", 19);
        userRepository.save(user);
        UserDTO userDTO = userService.getUserById(user.getId());

        assertNotNull(userDTO);
        assertEquals("Tim", userDTO.getName());
        assertEquals(19, userDTO.getAge());

    }

    @Test
    public void testGetUserByNameFailed() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByName("Tim");
        });

        assertEquals("No users with this name", exception.getMessage());
    }

    @Test
    public void testGetUserByName() {
        User user = new User("Tim", 19);
        userRepository.save(user);
        UserDTO userDTO = userService.getUserByName(user.getName());

        assertNotNull(userDTO);
        assertEquals("Tim", userDTO.getName());
        assertEquals(19, userDTO.getAge());

    }

    @Test
    public void testGetUserByAgeFailed() {
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUsersByAge(1);
        });

        assertEquals("No users with this age", exception.getMessage());
    }

    @Test
    public void testGetOneUserByAge() {
        User user = new User("Tim", 19);
        userRepository.save(user);
        List<UserDTO> userDTOS = userService.getUsersByAge(user.getAge());

        assertNotNull(userDTOS);
        assertFalse(userDTOS.isEmpty());
        assertEquals(userDTOS.size(), 1);
        assertEquals("Tim", userDTOS.getFirst().getName());

    }

    @Test
    public void testGetUsersByAge() {
        User user1 = new User("Tim", 23);
        User user2 = new User("Jess", 19);
        User user3 = new User("Sam", 19);
        User user4 = new User("Bob", 19);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        List<UserDTO> userDTOS = userService.getUsersByAge(user2.getAge());

        assertNotNull(userDTOS);
        assertFalse(userDTOS.isEmpty());
        assertEquals(userDTOS.size(), 3);
        assertEquals("Jess", userDTOS.getFirst().getName());

    }

}
