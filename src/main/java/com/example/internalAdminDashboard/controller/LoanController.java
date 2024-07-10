package com.example.internalAdminDashboard.controller;

import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<LoanDTO>> GetLoansByAmount(@PathVariable String amount) {
        List<LoanDTO> loans = loanService.getLoansByAmount(Integer.parseInt(amount));
        LOGGER.info("Loans returned from GetLoansByAmount is {}", loans);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{loanPeriod}")
    public ResponseEntity<List<LoanDTO>> GetLoansByLoanPeriod(@PathVariable String loanPeriod) {
        List<LoanDTO> loans = loanService.getLoansByLoanPeriod(Integer.parseInt(loanPeriod));
        LOGGER.info("Loans returned from GetLoansByLoanPeriod is {}", loans);
        return ResponseEntity.ok(loans);
    }

}