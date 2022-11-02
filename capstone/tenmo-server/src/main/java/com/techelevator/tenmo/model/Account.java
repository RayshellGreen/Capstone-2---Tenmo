package com.techelevator.tenmo.model;

import com.techelevator.tenmo.dao.JdbcUserDao;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private BigDecimal Balance;



    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.Balance = balance;
    }

    public Account() {}

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }


}
