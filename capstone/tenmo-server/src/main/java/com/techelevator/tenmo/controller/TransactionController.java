package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.exception.InsufficientFundsException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
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

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
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
    @GetMapping("/user/{id}")
    public List<Transaction> getTransactionsByUserId(@PathVariable int userid) {
        List<Transaction> transactions = transactionDao.getTransactionsByUserId(userid);
        return transactions;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/send")
    public void sendFunds(@RequestBody Transaction transaction) throws InsufficientFundsException {

        Account sender = accountDao.findAccountByUserId(transaction.getSenderId());
        Account receiver = accountDao.findAccountByUserId(transaction.getReceiverId());
        if (sender != null && receiver != null) {
            if (accountDao.hasSufficientFunds(sender.getUserId(), transaction.getAmount())) {
                accountDao.addToBalance(transaction.getAmount(), receiver.getUserId());
                accountDao.subtractFromBalance(transaction.getAmount(), sender.getUserId());
                transactionDao.sendFunds(transaction);
            } else {
                throw new InsufficientFundsException();
            }
        }


    }
}
