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
    public Account findByAccountId(@PathVariable long id) {
        Account account =  accountDao.findByAccountId(id);
        if (account == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        return this.accountDao.findByAccountId(id);
    }

    @GetMapping("/{id}")
    public Account getBalanceByAccountId(@PathVariable long id) {
        return this.accountDao.findByAccountId(id);
    }



    //Below is for future creation
/*
TODO  @GetMapping("/{id}")
    public Account getBalanceByAccountId(@PathVariable long id) {
        return this.accountDao.getBalanceByAccountId(id);
    }
*/


}
