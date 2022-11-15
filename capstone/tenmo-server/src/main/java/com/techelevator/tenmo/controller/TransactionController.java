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

        return null;
    }

    //TODO
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{id}")
    public List<Transaction> getTransactionsByUserId() {
        return null;
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
//    @PostMapping("/send") //TODO put into JDBC?? Idont think so it is calling on other methods that will go into JDBC
//    public void sendFunds(@RequestBody Transaction transaction throws InsufficientFundsException {//replaced Transaction transaction parameter
//        Account sender = accountDao.findAccountByUserId(transaction.getSenderId());
//        Account receiver = accountDao.findAccountByUserId(transaction.getReceiverId());
//        if (sender != null && receiver != null) {
//            if (accountDao.hasSufficientFunds(transaction.getSenderId(), transaction.getAmount())) {
//                transactionDao.sendFunds(senderId, receiverId, amount);
//            }
//        } else {
//            throw new InsufficientFundsException();
//
//    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/send")
    public void sendFunds(@RequestBody Transaction transaction) throws InsufficientFundsException {

//            transactionDao.sendFunds(transaction);
//            try{
//                updateAccountBalances(transaction.getSenderId(), transaction.getReceiverId(), transaction.getAmount());
//            } catch (InsufficientFundsException ife) {
//            System.out.println(ife);
//        }


        Account sender = accountDao.findAccountByUserId(transaction.getSenderId());
        Account receiver = accountDao.findAccountByUserId(transaction.getReceiverId());
        if (sender != null && receiver != null) {
            if (accountDao.hasSufficientFunds(sender.getUserId(), transaction.getAmount())) {
                accountDao.addToBalance(transaction.getAmount(), receiver.getUserId());
                accountDao.subtractFromBalance(transaction.getAmount(), sender.getUserId());
                transactionDao.sendFunds(transaction);
//            }
            } else {
                throw new InsufficientFundsException();
            }
        }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @PostMapping("") //TODO need to thow an error if user don't have money
//    public Transaction createTransaction(@RequestBody Transaction transaction) {
//        Transaction transaction1 = transactionDao.createTransaction(transaction);
//        try {
//            sendFunds(transaction);
//        } catch (InsufficientFundsException ife) {
//            System.out.println(ife);
//
//        }
//        return transaction1;
//    }

    }
    //TODO might have to rework senderId.receiverID. Idk if its actually pointing to the right thing
//    @PutMapping("") //TODO put something here
//    private void updateAccountBalances(int senderId, int receiverId, BigDecimal amount) throws InsufficientFundsException {
//        if (accountDao.havesSufficientFunds(senderId, amount)) {
//            accountDao.addToBalance(amount, senderId);
//            accountDao.subtractFromBalance(amount, receiverId);
//        }
//    }

}
