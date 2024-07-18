package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.exception.LoanNotFoundException;
import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.helper.LoanHelpers;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceUnitTest {
    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanHelpers loanHelpers;

    @InjectMocks
    private LoanService loanService;

    @Test
    public void testGetLoanByAmountFailed() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);

        List<Loan> loans = List.of(loan1);
        when(loanRepository.findAllLoansByAmount(200)).thenThrow(LoanNotFoundException.class);

//        List<LoanDTO> actualLoanDTOs = loanService.getLoansByAmount(5000);

        assertThrows(LoanNotFoundException.class, () -> loanService.getLoansByAmount(200));
    }

    @Test
    public void testGetOneLoanByAmount() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);

        List<Loan> loans = List.of(loan1);
        when(loanRepository.findAllLoansByAmount(5000)).thenReturn(loans);

        List<LoanDTO> expectedLoanDTOs = new ArrayList<>(List.of());
        for (Loan loan: loans) {
            LoanDTO loanDTO = new LoanDTO(loan.getAmount(), loan.getLoanPeriod(), loan.getUser());
            when(loanHelpers.entityToLoanDTO(loan)).thenReturn(loanDTO);
            expectedLoanDTOs.add(loanDTO);
        }

        List<LoanDTO> actualLoanDTOs = loanService.getLoansByAmount(5000);

        assertNotNull(actualLoanDTOs);
        assertFalse(actualLoanDTOs.isEmpty());
        assertEquals(expectedLoanDTOs.size(), actualLoanDTOs.size());
        assertEquals(expectedLoanDTOs.get(0).getUser().getName(), actualLoanDTOs.get(0).getUser().getName());

    }

    @Test
    public void testGetLoansByAmount() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);
        Loan loan2 = new Loan(5000, 6, user2);
        Loan loan3 = new Loan(5000, 12, user3);
        Loan loan4 = new Loan(3000, 3, user1);

        List<Loan> loans = List.of(loan1, loan2, loan3);
        when(loanRepository.findAllLoansByAmount(5000)).thenReturn(loans);

        List<LoanDTO> expectedLoanDTOs = new ArrayList<>(List.of());
        for (Loan loan: loans) {
            LoanDTO loanDTO = new LoanDTO(loan.getAmount(), loan.getLoanPeriod(), loan.getUser());
            when(loanHelpers.entityToLoanDTO(loan)).thenReturn(loanDTO);
            expectedLoanDTOs.add(loanDTO);
        }

        List<LoanDTO> actualLoanDTOs = loanService.getLoansByAmount(5000);

        assertNotNull(actualLoanDTOs);
        assertFalse(actualLoanDTOs.isEmpty());
        assertEquals(expectedLoanDTOs.size(), actualLoanDTOs.size());
        assertEquals(expectedLoanDTOs.get(0).getUser().getName(), actualLoanDTOs.get(0).getUser().getName());

    }

    @Test
    public void testGetLoanByLoanPeriodFailed() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);

        List<Loan> loans = List.of(loan1);
        when(loanRepository.findAllLoansByLoanPeriod(18)).thenThrow(LoanNotFoundException.class);

        assertThrows(LoanNotFoundException.class, () -> loanService.getLoansByLoanPeriod(18));
    }

    @Test
    public void testGetOneLoanByLoanPeriod() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);

        List<Loan> loans = List.of(loan1);
        when(loanRepository.findAllLoansByLoanPeriod(6)).thenReturn(loans);

        List<LoanDTO> expectedLoanDTOs = new ArrayList<>(List.of());
        for (Loan loan: loans) {
            LoanDTO loanDTO = new LoanDTO(loan.getAmount(), loan.getLoanPeriod(), loan.getUser());
            when(loanHelpers.entityToLoanDTO(loan)).thenReturn(loanDTO);
            expectedLoanDTOs.add(loanDTO);
        }

        List<LoanDTO> actualLoanDTOs = loanService.getLoansByLoanPeriod(6);

        assertNotNull(actualLoanDTOs);
        assertFalse(actualLoanDTOs.isEmpty());
        assertEquals(expectedLoanDTOs.size(), actualLoanDTOs.size());
        assertEquals(expectedLoanDTOs.get(0).getUser().getName(), actualLoanDTOs.get(0).getUser().getName());

    }

    @Test
    public void testGetLoansByLoanPeriod() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);
        Loan loan2 = new Loan(5000, 6, user2);
        Loan loan3 = new Loan(5000, 12, user3);
        Loan loan4 = new Loan(3000, 3, user1);

        List<Loan> loans = List.of(loan1, loan2, loan3);
        when(loanRepository.findAllLoansByLoanPeriod(6)).thenReturn(loans);

        List<LoanDTO> expectedLoanDTOs = new ArrayList<>(List.of());
        for (Loan loan: loans) {
            LoanDTO loanDTO = new LoanDTO(loan.getAmount(), loan.getLoanPeriod(), loan.getUser());
            when(loanHelpers.entityToLoanDTO(loan)).thenReturn(loanDTO);
            expectedLoanDTOs.add(loanDTO);
        }

        List<LoanDTO> actualLoanDTOs = loanService.getLoansByLoanPeriod(6);

        assertNotNull(actualLoanDTOs);
        assertFalse(actualLoanDTOs.isEmpty());
        assertEquals(expectedLoanDTOs.size(), actualLoanDTOs.size());
        assertEquals(expectedLoanDTOs.get(0).getUser().getName(), actualLoanDTOs.get(0).getUser().getName());

    }

    @Test
    public void testCreateAndAddLoanToUserFailed() {
        Loan loan = new Loan(3000, 3, null);

        assertThrows(UserNotFoundException.class, () -> loanService.createAndAddLoanToUser(loan, null));
    }

    @Test
    public void testCreateAndAddLoanToUser() {
        User user = new User("Tim", 19);
        Loan loan = new Loan(3000, 3, user);

        when(loanRepository.save(loan)).thenReturn(loan);

        loanService.createAndAddLoanToUser(loan, user);

        verify(loanRepository).save(loan);
    }
}

