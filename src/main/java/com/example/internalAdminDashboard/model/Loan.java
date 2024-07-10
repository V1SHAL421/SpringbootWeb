package com.example.internalAdminDashboard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;
    private Integer amount;
    private Integer loanPeriod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Loan() {}

    public Loan(Integer amount, Integer loanPeriod, User user) {
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
