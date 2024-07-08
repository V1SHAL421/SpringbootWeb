package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class InfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);
    @Autowired
    private final UserRepository userRepository;

    public InfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//     This is for client-side rendering approach
    @GetMapping("/users")
    public List<User> getAllUsers() {
        LOGGER.info("getAllUsers accessed!");
            return userRepository.findAll();

    }

    @GetMapping("/{name}")
    public User getUserByName(@RequestParam String name) {
        LOGGER.info("getUserByName accessed");
        return userRepository.findUserByName(name);
    }

    @GetMapping("/age")
    public List<User> getUsersByAge(@RequestParam Integer age) {
        return userRepository.findUsersByAge(age);
    }

    @PostMapping("/{name}")
    public String postUser(@RequestBody User user) {
        this.user = user;
        return "User successfully created";
    }
}







//    public User getUserDetails(@RequestParam(name = "name", required = true), String name) {
//        return new UserInfo(name);
//    }














// This is for server-side rendering approach
//    @GetMapping("/users")
//    public String GetUserDetails(@RequestParam(name = "name", // Binds value of query string param into name param of method
//    required = false, defaultValue = "Unavailable") String name, Model model) {
//        // Required = false is that the query string parameter is not required
//        // If it is not there, the default value for it is "Unavailable"
//        model.addAttribute("name", name); // Value of name param added to model making it accessible to view template
//        return "name"; // Returns name.html view relying on view tech (Thymeleaf). The name variable is passed through as well.
//    }