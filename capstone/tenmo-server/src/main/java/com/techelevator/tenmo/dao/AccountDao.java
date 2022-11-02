package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    Account create(int user_id);

    List<Account> findAll();

    Account findAccountByUserId(int id);//  Referencing User

    Account getBalanceByUserId(int id);
}
