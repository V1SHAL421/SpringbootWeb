package com.example.internalAdminDashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.internalAdminDashboard.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
