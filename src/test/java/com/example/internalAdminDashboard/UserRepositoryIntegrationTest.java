package com.example.internalAdminDashboard;

import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryIntegrationTest.class);
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveOneUser() {
        userRepository.save(new User("Vishal", 20));
        List<User> users = (List<User>) userRepository.findAll();
        LOGGER.info("The users returned are {}", users);

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void saveMultipleUsers() {
        userRepository.save(new User("Vishal", 20));
        userRepository.save(new User("Ryan", 30));
        List<User> users = userRepository.findAll();
        LOGGER.info("The users returned are {}", users);

        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void retrieveUserByName() {
        userRepository.save(new User("Ben", 20));
        userRepository.save(new User("Vishal", 20));
        userRepository.save(new User("Ryan", 30));
        User user = userRepository.findUserByName("Ryan");
        LOGGER.info("The user returned is {}", user);

        assertThat(user.getName()).isEqualTo("Ryan");
    }

    @Test
    public void retrieveOneUserByAge() {
        userRepository.save(new User("Ben", 20));
        userRepository.save(new User("Vishal", 20));
        userRepository.save(new User("Ryan", 30));
        List<User> users = userRepository.findUsersByAge(30);
        LOGGER.info("The users returned are {}", users);

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    public void retrieveUsersByAge() {
        userRepository.save(new User("Ben", 20));
        userRepository.save(new User("Vishal", 20));
        userRepository.save(new User("Ryan", 30));
        List<User> users = userRepository.findUsersByAge(20);
        LOGGER.info("The users returned are {}", users);

        assertThat(users.size()).isEqualTo(2);
    }
}
