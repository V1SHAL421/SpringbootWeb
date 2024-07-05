package com.example.internalAdminDashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Ready to be used by Spring MVC to handle web requests. Combines controller and response body
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello everyone!";
    }

    @GetMapping("/welcome")
    public String welcome() { return "Welcome to my website"; }
}
