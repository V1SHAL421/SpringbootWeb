package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InfoController.class)
public class InfoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;  // Converts objects to JSON strings

    @MockBean
    private UserService userService;

    @Test
    public void testGetUsers() throws Exception {
        List<UserDTO> userDTOS = List.of(new UserDTO("Tim", 27));
        when(userService.getAllUsers()).thenReturn(userDTOS);

        mvc.perform(MockMvcRequestBuilders.get("/user/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDTOS)));
    }

    @Test
    public void testGetUserByName() throws Exception {
        UserDTO userDTO = new UserDTO("Tim", 27);
        when(userService.getUserByName("Tim")).thenReturn(userDTO);

        mvc.perform(MockMvcRequestBuilders.get("/user/{name}", "Tim").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDTO)));
    }

    @Test
    public void testGetUsersByAge() throws Exception {
        List<UserDTO> userDTOs = List.of(new UserDTO("Tim", 27));
        when(userService.getUsersByAge(27)).thenReturn(userDTOs);

        mvc.perform(MockMvcRequestBuilders.get("/user/age/{age}", 27).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDTOs)));
    }


}
