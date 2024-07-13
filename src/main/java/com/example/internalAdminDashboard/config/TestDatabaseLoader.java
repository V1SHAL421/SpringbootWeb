package com.example.internalAdminDashboard.config;

import com.example.internalAdminDashboard.repository.LoanRepository;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Configuration
@Profile("test") // Active when test profile is active
public class TestDatabaseLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public TestDatabaseLoader(UserRepository userRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void run(String... args) throws Exception {}
    }

