package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping ("/transfer")
public class TransactionController {
    private TransactionDao transactionDao;
    private AccountDao accountDao;

    public TransactionController(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    @GetMapping("/{id}")
    public List<Transaction> findAll() {
        return this.transactionDao.findAll();
    }

    @GetMapping("/{id}")
    public List<Transaction> getTransactionsByTransactionId(@ PathVariable int transactionId) {
        List<Transaction> transactions = transactionDao.getTransactionsByTransactionId(transactionId);
        
        return null;
    }

    @GetMapping("/{id}")
    public List<Transaction> getTransactionsByUserId() {
        return null;
    }


    @PostMapping("") //TODO securing API chapter
    public Transaction startTransactionByUserId(@RequestBody Transaction transaction, Principal principal) {

        return  null;
    }

}
