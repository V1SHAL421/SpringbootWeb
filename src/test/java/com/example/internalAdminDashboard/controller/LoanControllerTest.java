package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.service.LoanService;
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

@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;  // Converts objects to JSON strings

    @MockBean
    private LoanService loanService;

    @Test
    public void testGetLoansByAmount() throws Exception {
        User user = new User("Tim", 27);
        LoanDTO loanDTO = new LoanDTO(4000, 6, user);

        List<LoanDTO> loanDTOS = List.of(loanDTO);
        when(loanService.getLoansByAmount(4000)).thenReturn(loanDTOS);

        mvc.perform(MockMvcRequestBuilders.get("/loan/amount/{amount}", 4000).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(loanDTOS)));

    }

    @Test
    public void testGetLoansByLoanPeriod() throws Exception {
        User user = new User("Tim", 27);
        LoanDTO loanDTO = new LoanDTO(4000, 6, user);

        List<LoanDTO> loanDTOS = List.of(loanDTO);
        when(loanService.getLoansByLoanPeriod(6)).thenReturn(loanDTOS);

        mvc.perform(MockMvcRequestBuilders.get("/loan//{loanPeriod}", 6).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(loanDTOS)));

    }

}
