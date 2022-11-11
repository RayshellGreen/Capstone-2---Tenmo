package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {


     List<Account> listAllAccounts();

     Account findAccountByUserId(int userId);//  Referencing User

     BigDecimal getBalanceByUserId(int userId);

    void addToBalance(BigDecimal amount, int userId);

    void subtractFromBalance(BigDecimal amount, int userId);

    boolean hasSufficientFunds(int userId, BigDecimal transferAmount);




}
