package com.example.internalAdminDashboard.dto;

import com.example.internalAdminDashboard.model.User;

public class LoanDTO {
    private Long id;
    private Integer amount;
    private Integer loanPeriod;
    private User user;

    public LoanDTO() {}

    public LoanDTO(Integer amount, Integer loanPeriod, User user) {
        this.amount = amount;
        this.loanPeriod = loanPeriod;
        this.user = user;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
