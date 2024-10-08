package com.example.internalAdminDashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.internalAdminDashboard.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    List<User> getAllUsers();
    User findUserByName(String name);
    List<User> findUsersByAge(Integer age);
    User findUserById(Long id);
}
