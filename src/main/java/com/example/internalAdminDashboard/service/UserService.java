package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    // We do not perform field injections because
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        LOGGER.info("getAllUsers accessed");
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users in database");
            // The UserExceptionController auto intercepts these exceptions and handles them
        }
        return users;
    }

    public User getUserByName(String name) {
        LOGGER.info("getUserByName accessed with name: {}", name);
        return userRepository.findUserByName(name);
    }

    public List<User> getUsersByAge(Integer age) {
        LOGGER.info("getUsersByAge accessed with age: {}", age);
        List<User> users = userRepository.findUsersByAge(age);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users with this age");
        }
        return users;
    }

    public void PopulateUsers() {
        User user1 = new User("Alice", 22);
        User user2 = new User("Bob", 25);
        User user3 = new User ("Charlie", 27);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }



}
