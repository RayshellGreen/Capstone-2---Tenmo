package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.apache.tomcat.jni.BIOCallback;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/account")
public class AccountController {
    private AccountDao accountDao;
    private UserDao userDao;
    private TransactionDao transactionDao;


    public AccountController(AccountDao accountDao, UserDao userDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("")
    public List<Account> getAccountList() {
        return this.accountDao.listAllAccounts();
    }

    //* TODO is this needed? Maybe should be username?
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{userId}")
    public Account findAccountByUserId(@PathVariable int userId) {
        Account account = accountDao.findAccountByUserId(userId);
        if (account == null) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        return this.accountDao.findAccountByUserId(userId);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{userId}/balance")
    public BigDecimal getBalanceByUserId(@PathVariable int userId) {
        BigDecimal accountBalance = accountDao.getBalanceByUserId(userId);
        if (accountBalance == null) {
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return this.accountDao.getBalanceByUserId(userId);
    }

}
