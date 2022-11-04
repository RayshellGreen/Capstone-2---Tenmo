package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    List<Transaction> findAll();

    List<Transaction> getTransactionByTransactionId(int transactionId);

    List<Transaction> getTransactionsByUserId(int userId);

    Transaction createTransaction(int senderUserId, int receiverUserId, BigDecimal amount);
}
