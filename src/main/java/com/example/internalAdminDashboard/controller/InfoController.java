package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/user")
public class InfoController {

    @Autowired
    private UserRepository userRepository;

//     This is for client-side rendering approach
    @GetMapping("/users")
    public List<User> getAllUsers() {
            return userRepository.findAll();

    }

    @GetMapping("/{name}")
    public User getUserByName(@RequestParam String name) {
        return userRepository.findUserByName(name);
    }

    @GetMapping("/age")
    public List<User> getUsersByAge(@RequestParam Integer age) {
        return userRepository.findUsersByAge(age);
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