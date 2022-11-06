package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.apache.tomcat.jni.BIOCallback;
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
    private TransactionDao transactionDao;


    public AccountController(AccountDao accountDao, UserDao userDao, TransactionDao transactionDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transactionDao = transactionDao;
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
    @PutMapping("/{id}") //TODO - Come Back
    public BigDecimal addToBalance(@PathVariable int userId, @RequestParam BigDecimal amount) {
        Account userAccount = accountDao.findAccountByUserId(userId);
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return accountDao.addToBalance(userId, amount);

    }//if the id that fed into the method is not equl to the id of the princi is not the user account
    //

    @PutMapping("/{id}")
    public BigDecimal subtractFromBalance(@PathVariable int userId, @RequestParam BigDecimal amount) {
        Account userAccount = accountDao.findAccountByUserId(userId);
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return accountDao.subtractFromBalance(userId, amount);
    }



    //Below is for future creation
/*
TODO  @GetMapping("/{id}")
    public Account getBalanceByAccountId(@PathVariable long id) {
        return this.accountDao.getBalanceByAccountId(id);
    }
*/


}
