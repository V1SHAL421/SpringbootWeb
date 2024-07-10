package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class InfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoController.class);
    private final UserService userService;
    @Autowired
    public InfoController(UserService userService) {
        this.userService = userService;
    }

//     This is for client-side rendering approach
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> GetUsers() {
        List<UserDTO> users = userService.getAllUsers();
        LOGGER.info("Users returned from GetUsers() is {}", users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserDTO> GetUserFromName(@PathVariable String name) {
        UserDTO user = userService.getUserByName(name);
        LOGGER.info("User returned from GetUserFromName is {}", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<UserDTO>> GetUsersByAge(@PathVariable String age) {
        List<UserDTO> users = userService.getUsersByAge(Integer.parseInt(age));
        LOGGER.info("getUsersByAge accessed with age: {}", age);
        return ResponseEntity.ok(users);
    }

//    @PostMapping("/create")
//    public User createUser(@RequestBody User user) {
//        LOGGER.info("createUser is called with the user: {}", user);
//        return userRepository.save(user);
//    }

//    @PostMapping("/{name}")
//    public String postUser(@RequestBody User user) {
//        this.user = user;
//        return "User successfully created";
//    }
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