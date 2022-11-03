package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountDao accountDao;
    private UserDao userDao;


    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @GetMapping("")
    public List<Account> getAccountList() {
        return this.accountDao.findAll();
    }


    @GetMapping("/{id}")
    public Account findAccountByUserId(@PathVariable int userId) {
        Account account = accountDao.findAccountByUserId(userId);
        if (account == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        return this.accountDao.findAccountByUserId(userId);
    }

    @GetMapping("/{id}")
    public BigDecimal getBalanceByUserId(@PathVariable int userId) {
        BigDecimal accountBalance = accountDao.getBalanceByUserId(userId);
        if (accountBalance == null) { //TODO should this be a sout/ error handling
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return this.accountDao.getBalanceByUserId(userId);
    }



    //Below is for future creation
/*
TODO  @GetMapping("/{id}")
    public Account getBalanceByAccountId(@PathVariable long id) {
        return this.accountDao.getBalanceByAccountId(id);
    }
*/


}
