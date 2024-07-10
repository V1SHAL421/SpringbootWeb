package com.example.internalAdminDashboard.service;

import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.exception.LoanNotFoundException;
import com.example.internalAdminDashboard.exception.UserNotFoundException;
import com.example.internalAdminDashboard.helper.LoanHelpers;
import com.example.internalAdminDashboard.model.Loan;
import com.example.internalAdminDashboard.model.User;
import com.example.internalAdminDashboard.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);
    private final LoanRepository loanRepository;
    private final LoanHelpers loanHelpers;
    private final UserService userService;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserService userService, LoanHelpers loanHelpers) {
        this.loanRepository = loanRepository;
        this.userService = userService;
        this.loanHelpers = loanHelpers;
    }

    public List<LoanDTO> getLoansByAmount(Integer amount) {
        LOGGER.info("getLoansByAmount accessed with amount: {}", amount);
        List<Loan> loans = loanRepository.findAllLoansByAmount(amount);
        if (loans.isEmpty()) {
            throw new LoanNotFoundException("No loans with this amount");
        }
        List<LoanDTO> loanDTOS = new ArrayList<>(List.of());
        for (Loan loan : loans) {
            loanDTOS.add(loanHelpers.entityToLoanDTO(loan));
        }
        return loanDTOS;
    }

    public List<LoanDTO> getLoansByLoanPeriod(Integer loanPeriod) {
        LOGGER.info("getLoansByLoanPeriod accessed with loan period: {}", loanPeriod);
        List<Loan> loans = loanRepository.findAllLoansByLoanPeriod(loanPeriod);
        if (loans.isEmpty()) {
            throw new LoanNotFoundException("No loans with this loan period");
        }
        List<LoanDTO> loanDTOS = new ArrayList<>(List.of());
        for (Loan loan : loans) {
            loanDTOS.add(loanHelpers.entityToLoanDTO(loan));
        }
        return loanDTOS;
    }


    @Transactional
    public void createAndAddLoanToUser(Loan loan, User user) {
//        UserDTO user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("No user with this id");
        }
        loan.setUser(user);
        loanRepository.save(loan);
    }
}