package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.helper.UserHelpers;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.LoanRepository;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    // We do not perform field injections because
    private final UserRepository userRepository;
    private final UserHelpers userHelpers;
    @Autowired
    public UserService(UserRepository userRepository, UserHelpers userHelpers) {
        this.userRepository = userRepository; this.userHelpers = userHelpers;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        LOGGER.info("getAllUsers accessed");
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users in database");
            // The UserExceptionController auto intercepts these exceptions and handles them
        }
        List<UserDTO> userDTOS = new ArrayList<>(List.of());
        for (User user: users) {
            userDTOS.add(userHelpers.entityToUserDTO(user));
        }
        return userDTOS;
    }

    public UserDTO getUserById(Long id) {
        LOGGER.info("getUserById accessed with id: {}", id);
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException("No users with this id");
        }
        return userHelpers.entityToUserDTO(user);
    }

    public UserDTO getUserByName(String name) {
        LOGGER.info("getUserByName accessed with name: {}", name);
        User user = userRepository.findUserByName(name);
        if (user == null) {
            throw new UserNotFoundException("No users with this age");
        }
        return userHelpers.entityToUserDTO(user);
    }

    public List<UserDTO> getUsersByAge(Integer age) {
        LOGGER.info("getUsersByAge accessed with age: {}", age);
        List<User> users = userRepository.findUsersByAge(age);
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users with this age");
        }
        List<UserDTO> userDTOS = new java.util.ArrayList<>(List.of());
        for (User user: users) {
            userDTOS.add(userHelpers.entityToUserDTO(user));
        }
        return userDTOS;
    }



}
