package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {


    List<Account> findAll();

    Account findAccountByUserId(int userId);//  Referencing User

    BigDecimal getBalanceByUserId(int userId);
}
