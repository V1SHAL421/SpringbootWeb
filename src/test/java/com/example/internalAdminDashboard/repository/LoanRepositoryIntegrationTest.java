package com.example.internalAdminDashboard.repository;

import com.example.internalAdminDashboard.exception.LoanException;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LoanRepositoryIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanRepositoryIntegrationTest.class);
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveOneLoan() {
        User user = userRepository.save(new User("Dan", 19));
        loanRepository.save(new Loan(25000, 12, user));
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans.size()).isEqualTo(1);
    }

    @Test
    public void saveLoansForOneUser() {
        User user = userRepository.save(new User("Dan", 19));
        loanRepository.save(new Loan(20000, 12, user));
        loanRepository.save(new Loan(5000, 12, user));
        loanRepository.save(new Loan(25000, 12, user));
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans.size()).isEqualTo(3);
    }

    @Test
    public void saveLoansForUsers() {
        User user1 = userRepository.save(new User("Dan", 19));
        User user2 = userRepository.save(new User("Ryan", 29));
        loanRepository.save(new Loan(20000, 12, user1));
        loanRepository.save(new Loan(220000, 6, user1));
        loanRepository.save(new Loan(5000, 12, user2));
        loanRepository.save(new Loan(25000, 12, user2));
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans.size()).isEqualTo(4);
    }

    @Test
    public void retrieveLoanByAmount() {
        User user1 = userRepository.save(new User("Dan", 19));
        User user2 = userRepository.save(new User("Ryan", 29));
        loanRepository.save(new Loan(20000, 12, user1));
        loanRepository.save(new Loan(220000, 6, user1));
        loanRepository.save(new Loan(5000, 12, user2));
        loanRepository.save(new Loan(25000, 12, user2));
        List<Loan> loans = loanRepository.findAllLoansByAmount(25000);
        assertThat(loans.size()).isEqualTo(1);
    }

    @Test
    public void retrieveLoansByAmount() {
        User user1 = userRepository.save(new User("Dan", 19));
        User user2 = userRepository.save(new User("Ryan", 29));
        loanRepository.save(new Loan(20000, 12, user1));
        loanRepository.save(new Loan(220000, 6, user1));
        loanRepository.save(new Loan(5000, 3, user2));
        loanRepository.save(new Loan(5000, 12, user2));
        List<Loan> loans = loanRepository.findAllLoansByAmount(5000);
        assertThat(loans.size()).isEqualTo(2);
    }

    @Test
    public void retrieveLoanByLoanPeriod() {
        User user1 = userRepository.save(new User("Dan", 19));
        User user2 = userRepository.save(new User("Ryan", 29));
        loanRepository.save(new Loan(20000, 12, user1));
        loanRepository.save(new Loan(220000, 6, user1));
        loanRepository.save(new Loan(5000, 12, user2));
        loanRepository.save(new Loan(25000, 12, user2));
        List<Loan> loans = loanRepository.findAllLoansByLoanPeriod(6);
        assertThat(loans.size()).isEqualTo(1);
    }

    @Test
    public void retrieveLoansByLoanPeriod() {
        User user1 = userRepository.save(new User("Dan", 19));
        User user2 = userRepository.save(new User("Ryan", 29));
        loanRepository.save(new Loan(20000, 12, user1));
        loanRepository.save(new Loan(220000, 6, user1));
        loanRepository.save(new Loan(5000, 3, user2));
        loanRepository.save(new Loan(5000, 12, user2));
        List<Loan> loans = loanRepository.findAllLoansByLoanPeriod(12);
        assertThat(loans.size()).isEqualTo(2);
    }


}
