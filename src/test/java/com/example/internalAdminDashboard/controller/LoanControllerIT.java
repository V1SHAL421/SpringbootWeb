package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.InternalAdminDashboardApplication;
import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.LoanRepository;
import com.example.internalAdminDashboard.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("test")
@SpringBootTest(classes = InternalAdminDashboardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanControllerIT.class);

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @BeforeEach
    public void setUp() {
        User user1 = new User("Tim", 19);
        User user2 = new User("Eric", 24);
        User user3 = new User("Nick", 23);
        Loan loan1 = new Loan(5000, 6, user1);
        Loan loan2 = new Loan(5000, 6, user2);
        Loan loan3 = new Loan(5000, 12, user3);
        Loan loan4 = new Loan(3000, 3, user1);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        loanRepository.save(loan1);
        loanRepository.save(loan2);
        loanRepository.save(loan3);
        loanRepository.save(loan4);

    }

    @AfterEach
    public void tearDown() {
        loanRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getLoansByAmount() throws Exception {
//        List<UserDTO> userDTOS = List.of(new UserDTO("Tim", 19), new UserDTO("Eric", 24), new UserDTO("James", 23));
        List<LoanDTO> loanDTOS = List.of(new LoanDTO(5000, 6, new User("Tim", 19)), new LoanDTO(5000, 6, new User("Eric", 23)), new LoanDTO(5000, 12, new User("James", 23)));

        ResponseEntity<List<LoanDTO>> response =
                template.exchange("/loan/amount/{amount}", HttpMethod.GET, null, new ParameterizedTypeReference<List<LoanDTO>>() {}, "5000");

        LOGGER.info("The response body is: {}", response.getBody());
        LOGGER.info("The expected response is: {}", loanDTOS);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        assertThat(response.getBody()).size().isEqualTo(loanDTOS.size());
        assertThat(response.getBody().getFirst().getAmount()).isEqualTo(loanDTOS.getFirst().getAmount());
        assertThat(response.getBody().getFirst().getLoanPeriod()).isEqualTo(loanDTOS.getFirst().getLoanPeriod());
        assertThat(response.getBody().getLast().getAmount()).isEqualTo(loanDTOS.getLast().getAmount());
        assertThat(response.getBody().getLast().getLoanPeriod()).isEqualTo(loanDTOS.getLast().getLoanPeriod());
    }

    @Test
    public void getLoansByLoanPeriod() throws Exception {
//        List<UserDTO> userDTOS = List.of(new UserDTO("Tim", 19), new UserDTO("Eric", 24), new UserDTO("James", 23));
        List<LoanDTO> loanDTOS = List.of(new LoanDTO(5000, 6, new User("Tim", 19)), new LoanDTO(5000, 6, new User("Eric", 23)));

        ResponseEntity<List<LoanDTO>> response =
                template.exchange("/loan/{loanPeriod}", HttpMethod.GET, null, new ParameterizedTypeReference<List<LoanDTO>>() {}, "6");

        LOGGER.info("The response body is: {}", response.getBody());
        LOGGER.info("The expected response is: {}", loanDTOS);
        assertFalse(Objects.requireNonNull(response.getBody()).isEmpty());
        assertThat(response.getBody()).size().isEqualTo(loanDTOS.size());
        assertThat(response.getBody().getFirst().getAmount()).isEqualTo(loanDTOS.getFirst().getAmount());
        assertThat(response.getBody().getFirst().getLoanPeriod()).isEqualTo(loanDTOS.getFirst().getLoanPeriod());
        assertThat(response.getBody().getLast().getAmount()).isEqualTo(loanDTOS.getLast().getAmount());
        assertThat(response.getBody().getLast().getLoanPeriod()).isEqualTo(loanDTOS.getLast().getLoanPeriod());
    }

}
