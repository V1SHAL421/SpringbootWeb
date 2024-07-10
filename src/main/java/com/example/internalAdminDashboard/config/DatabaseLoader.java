package com.example.internalAdminDashboard.config;

import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import com.example.internalAdminDashboard.service.LoanService;
import com.example.internalAdminDashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Needs to be labelled as a component for Spring Boot to scan it
public class DatabaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final LoanService loanService;
    @Autowired
    public DatabaseLoader(UserRepository userRepository, LoanService loanService) {
        this.userRepository = userRepository;
        this.loanService = loanService;
    }
    @Override
    public void run(String... args) throws Exception{
        User user1 = new User("Alice", 22);
        User user2 = new User("Bob", 25);
        User user3 = new User ("Charlie", 27);

//
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        loanService.createAndAddLoanToUser(new Loan(4000, 12, user1), user1);
        loanService.createAndAddLoanToUser(new Loan(6000, 12, user1), user1);
        loanService.createAndAddLoanToUser(new Loan(50000, 18, user1), user1);
        loanService.createAndAddLoanToUser(new Loan(8000, 6, user2), user2);
        loanService.createAndAddLoanToUser(new Loan(10000, 12, user3), user3);

    }
}
