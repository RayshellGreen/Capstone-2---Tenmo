package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    List<Transaction> findAll();

    List<Transaction> getTransactionsByTransactionId(int transactionId);

    List<Transaction> getTransactionsByAccountId(int accountId);

    void sendFunds(Transaction transaction);

//    void updateAccountBalances(int senderId, int receiverId, BigDecimal amount);

    //TODO may need to uncomment
//    Transaction createTransaction(Transaction transaction);
}
