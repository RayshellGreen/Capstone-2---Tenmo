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

    @GetMapping("")
    public List<Account> getAccountList() {
        return this.accountDao.listAllAccounts();
    }

    //TODO is this needed? Maybe should be username?
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
        if (accountBalance == null) { //TODO should this be a sout/ error handling
           throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return this.accountDao.getBalanceByUserId(userId);
    }

    //TODO needs Preauth??
    @PutMapping("/balance/{receiverUserId}")
    public BigDecimal addToBalance(@PathVariable int receiverUserId, @RequestParam BigDecimal amount) {
        Account userAccount = accountDao.findAccountByUserId(receiverUserId);
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return accountDao.addToBalance(amount, receiverUserId);

    }//if the id that fed into the method is not equal to the id of the principle is not the user account
    //



//
//    public BigDecimal receiveFunds (@PathVariable int senderId, @PathVariable int receiverId @RequestParam BigDecimal transferAmount) {
//        Int currentBalance
//        Int finalBalance
//        currentBalance + transferAmount = finalBalance
//        final balance is the new value of the receivers's account balance
//
//    }
//
//    public BigDecimal transferFunds (@PathVariable int senderAccountId, @PathVariable int senderId, @PathVariable int receiverAccountId, @PathVariable int receiverId @RequestParam BigDecimal transferAmount) {
//        accountDao.sendFunds(senderId, receiverId, transferAmount);
//        accountDao.receiveFunds(senderId, receiverId, transferAmount);
//    }


    @PutMapping("/balance/{senderUserId}")
    public BigDecimal subtractFromBalance(@PathVariable int senderUserId, @RequestParam BigDecimal amount) {
        Account userAccount = accountDao.findAccountByUserId(senderUserId);
        if (userAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return accountDao.subtractFromBalance(amount, senderUserId);
    }
}
