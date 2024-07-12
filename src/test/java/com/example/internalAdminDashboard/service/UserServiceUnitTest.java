package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.helper.UserHelpers;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserHelpers userHelpers;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetOneUser() {
        User user = new User("Tim", 19);
        List<User> expectedUsers = List.of(user);
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<UserDTO> expectedUserDTOs = new java.util.ArrayList<>(List.of());
        for (User expectedUser: expectedUsers) {
            UserDTO userDTO = new UserDTO(expectedUser.getName(), expectedUser.getAge());
            when(userHelpers.entityToUserDTO(expectedUser)).thenReturn(userDTO);
            expectedUserDTOs.add(userDTO);
        }

        List<UserDTO> actualUsersDTOs = userService.getAllUsers();

        assertNotNull(actualUsersDTOs);
        assertFalse(actualUsersDTOs.isEmpty());
        assertEquals(expectedUserDTOs.size(), actualUsersDTOs.size());
        assertEquals("Tim", actualUsersDTOs.get(0).getName());

    }

    @Test
    public void testGetUsers() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        List<User> expectedUsers = List.of(user1, user2, user3);
        when(userRepository.findAll()).thenReturn(expectedUsers);
        List<UserDTO> expectedUserDTOs = new java.util.ArrayList<>(List.of());
        for (User expectedUser: expectedUsers) {
            UserDTO userDTO = new UserDTO(expectedUser.getName(), expectedUser.getAge());
            when(userHelpers.entityToUserDTO(expectedUser)).thenReturn(userDTO);
            expectedUserDTOs.add(userDTO);
        }

        List<UserDTO> actualUsersDTOs = userService.getAllUsers();

        assertNotNull(actualUsersDTOs);
        assertFalse(actualUsersDTOs.isEmpty());
        assertEquals(expectedUserDTOs.size(), actualUsersDTOs.size());
        assertEquals("Tim", actualUsersDTOs.get(0).getName());
        assertEquals("Nick", actualUsersDTOs.get(2).getName());

    }

    @Test
    public void testGetOneUserById() {
        User user = new User("Tim", 19);
        UserDTO expectedUserDTO = new UserDTO(user.getName(), user.getAge());
        when(userRepository.findUserById(user.getId())).thenReturn(user);
        when(userHelpers.entityToUserDTO(user)).thenReturn(expectedUserDTO);

        UserDTO actualUserDTO = userService.getUserById(user.getId());

        assertNotNull(actualUserDTO);
        assertEquals("Tim", actualUserDTO.getName());

    }

    @Test
    public void testGetUserByName() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Ben", 22);
        User user3 = new User("Jack", 24);
        UserDTO expectedUserDTO = new UserDTO(user2.getName(), user2.getAge());
        when(userRepository.findUserByName("Ben")).thenReturn(user2);
        when(userHelpers.entityToUserDTO(user2)).thenReturn(expectedUserDTO);

        UserDTO actualUserDTO = userService.getUserByName(user2.getName());

        assertNotNull(actualUserDTO);
        assertEquals("Ben", actualUserDTO.getName());
        assertEquals(22, actualUserDTO.getAge());

    }

    @Test
    public void testGetOneUserByAge() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        List<User> expectedUsers = List.of(user3);
        when(userRepository.findUsersByAge(user3.getAge())).thenReturn(expectedUsers);
        List<UserDTO> expectedUserDTOs = new java.util.ArrayList<>(List.of());
        for (User expectedUser: expectedUsers) {
            UserDTO userDTO = new UserDTO(expectedUser.getName(), expectedUser.getAge());
            when(userHelpers.entityToUserDTO(expectedUser)).thenReturn(userDTO);
            expectedUserDTOs.add(userDTO);
        }

        List<UserDTO> actualUsersDTOs = userService.getUsersByAge(user3.getAge());

        assertNotNull(actualUsersDTOs);
        assertFalse(actualUsersDTOs.isEmpty());
        assertEquals(expectedUserDTOs.size(), actualUsersDTOs.size());
        assertEquals("Nick", actualUsersDTOs.getFirst().getName());

    }

    @Test
    public void testGetUsersByAge() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 24);
        List<User> expectedUsers = List.of(user2, user3);
        when(userRepository.findUsersByAge(user3.getAge())).thenReturn(expectedUsers);
        List<UserDTO> expectedUserDTOs = new java.util.ArrayList<>(List.of());
        for (User expectedUser: expectedUsers) {
            UserDTO userDTO = new UserDTO(expectedUser.getName(), expectedUser.getAge());
            when(userHelpers.entityToUserDTO(expectedUser)).thenReturn(userDTO);
            expectedUserDTOs.add(userDTO);
        }

        List<UserDTO> actualUsersDTOs = userService.getUsersByAge(user3.getAge());

        assertNotNull(actualUsersDTOs);
        assertFalse(actualUsersDTOs.isEmpty());
        assertEquals(expectedUserDTOs.size(), actualUsersDTOs.size());
        assertEquals("Eric", actualUsersDTOs.getFirst().getName());
        assertEquals("Nick", actualUsersDTOs.getLast().getName());

    }

}
