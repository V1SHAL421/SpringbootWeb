package com.example.internalAdminDashboard.helper;

import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserHelpers {
    public UserDTO entityToUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getName(), user.getAge()
        );
    }
}
