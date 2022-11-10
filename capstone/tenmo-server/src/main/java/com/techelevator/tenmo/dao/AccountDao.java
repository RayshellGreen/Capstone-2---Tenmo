package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {


    public List<Account> listAllAccounts();

    public Account findAccountByUserId(int userId);//  Referencing User

    public BigDecimal getBalanceByUserId(int userId);

    BigDecimal addToBalance(BigDecimal amount, int userId);

    BigDecimal subtractFromBalance(BigDecimal amount, int userId);

    boolean hasSufficientFunds(int userId, BigDecimal transferAmount);




}
