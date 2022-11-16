package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.exception.InsufficientFundsException;
import com.techelevator.tenmo.exception.InvalidAmount;
import com.techelevator.tenmo.exception.InvalidTransfer;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionDao transactionDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao, UserDao userDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @GetMapping("")
    public List<Transaction> findAll() {
        return this.transactionDao.findAll();
    }

    //TODO
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{transactionId}")
    public List<Transaction> getTransactionsByTransactionId(@PathVariable int transactionId) {
        List<Transaction> transactions = transactionDao.getTransactionsByTransactionId(transactionId);

        return transactions;
    }

    //TODO
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable int accountId) {
        List<Transaction> transactions = transactionDao.getTransactionsByAccountId(accountId);

        return transactions;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/send")
    public void sendFunds(@RequestBody Transaction transaction) throws InvalidTransfer {

        Account sender = accountDao.findAccountByUserId(transaction.getSenderId());
        Account receiver = accountDao.findAccountByUserId(transaction.getReceiverId());


        if (sender != null && receiver != null) {
            if (sender.getUserId() != receiver.getUserId()) {
                try {
                    if (!accountDao.hasSufficientFunds(transaction.getSenderId(), transaction.getAmount())) {
                        throw new InsufficientFundsException();
                    } else if(transaction.getAmount().intValue() > 0 ) {
                        accountDao.subtractFromBalance(transaction.getAmount(), sender.getUserId());
                        accountDao.addToBalance(transaction.getAmount(), receiver.getUserId());
                        transactionDao.sendFunds(transaction);
                         } else {
                        throw new InvalidAmount();
                    }

                } catch (InsufficientFundsException | InvalidAmount e) {
                    e.printStackTrace();
                }
            } else {
                throw new InvalidTransfer();
            }

        }
    }
}


