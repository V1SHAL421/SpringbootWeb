package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.exception.LoanNotFoundException;
import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.helper.LoanHelpers;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.LoanRepository;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import jakarta.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class LoanServiceIT {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanHelpers loanHelpers;

    @Autowired
    private LoanService loanService;


    @Test
    public void testGetLoansByAmountFailed() {
        Exception exception = assertThrows(LoanNotFoundException.class, () -> {
            loanService.getLoansByAmount(1);
        });
        assertEquals("No loans with this amount", exception.getMessage());
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

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        loanRepository.save(loan1);
        loanRepository.save(loan2);
        loanRepository.save(loan3);
        loanRepository.save(loan4);

        List<LoanDTO> loanDTOs = loanService.getLoansByAmount(5000);

        assertNotNull(loanDTOs);
        assertFalse(loanDTOs.isEmpty());
        assertEquals(loanDTOs.size(), 3);
        assertEquals(loanDTOs.getFirst().getUser().getName(), "Tim");

    }

    @Test
    public void testGetLoansByLoanPeriodFailed() {
        Exception exception = assertThrows(LoanNotFoundException.class, () -> {
            loanService.getLoansByLoanPeriod(2);
        });
        assertEquals("No loans with this loan period", exception.getMessage());
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

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        loanRepository.save(loan1);
        loanRepository.save(loan2);
        loanRepository.save(loan3);
        loanRepository.save(loan4);

        List<LoanDTO> loanDTOs = loanService.getLoansByLoanPeriod(6);

        assertNotNull(loanDTOs);
        assertFalse(loanDTOs.isEmpty());
        assertEquals(loanDTOs.size(), 2);
        assertEquals(loanDTOs.getFirst().getUser().getName(), "Tim");
    }

    @Test
    public void testCreateAndAddLoanToUserFailed() {
        Loan loan = new Loan(5000, 6, null);

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            loanService.createAndAddLoanToUser(loan, null);
        });
        assertEquals("No user with this id", exception.getMessage());
    }

    @Test
    public void testCreateAndAddLoanToUser() {
        User user = new User("Tim", 19);
        Loan loan = new Loan(5000, 6, user);

        userRepository.save(user);
        loanService.createAndAddLoanToUser(loan, user);

        assertEquals(loanRepository.findAll().size(), 1);
        assertEquals(loanRepository.findAll().getFirst().getUser(), user);

    }






}
