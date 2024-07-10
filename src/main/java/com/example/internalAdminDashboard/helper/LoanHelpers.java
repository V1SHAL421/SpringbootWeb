package com.example.internalAdminDashboard.helper;

import com.example.internalAdminDashboard.dto.LoanDTO;
import com.example.internalAdminDashboard.dto.UserDTO;
import com.example.internalAdminDashboard.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanHelpers {

    public LoanDTO entityToLoanDTO(Loan loan) {
        if (loan == null) return null;
        UserDTO userDTO = new UserDTO(loan.getUser().getName(), loan.getUser().getAge());
        return new LoanDTO(
                loan.getAmount(),
                loan.getLoanPeriod(),
                loan.getUser()
        );
    }
}
