package com.example.internalAdminDashboard.config;

import com.example.internalAdminDashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Needs to be labelled as a component for Spring Boot to scan it
public class DatabaseLoader implements CommandLineRunner {
    private final UserService userService;
    @Autowired
    public DatabaseLoader(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void run(String... args) throws Exception{
        userService.PopulateUsers();
    }
}
