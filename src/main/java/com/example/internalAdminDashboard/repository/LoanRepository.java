package com.example.internalAdminDashboard.repository;

import com.example.internalAdminDashboard.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllLoansByAmount(Integer amount);
    List<Loan> findAllLoansByLoanPeriod(Integer loanPeriod);
}
