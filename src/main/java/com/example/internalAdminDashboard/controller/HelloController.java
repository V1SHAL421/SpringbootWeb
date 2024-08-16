package com.example.internalAdminDashboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Ready to be used by Spring MVC to handle web requests. Combines controller and response body
public class HelloController {
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public String hello() {
        return "Hello everyone!";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/welcome")
    public String welcome() { return "Welcome to my website"; }
}
