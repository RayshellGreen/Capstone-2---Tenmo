package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    List<Transaction> findAll();

    List<Transaction> getTransactionsByTransactionId(int transactionId);

    List<Transaction> getTransactionsByUserId(int userId);

    void sendFunds(Transaction transaction);

}
