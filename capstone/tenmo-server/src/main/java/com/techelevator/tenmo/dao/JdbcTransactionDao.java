package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> allTransactions = new ArrayList<>();

        final String sql = "SELECT transfer_id, account_id_sender, account_id_receiver, amount_transferred_out, amount_received FROM transfer; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transaction transaction = mapTransactionFromResult(results);
            allTransactions.add(transaction);
        }
        return allTransactions;
    }

    @Override
    public List<Transaction> getTransactionsByTransactionId (int transactionId) {
        List<Transaction> transactions = new ArrayList<>();
        final String sql = "SELECT transfer_id, account_id_sender, account_id_receiver, amount_transferred_out, amount_received FROM transfer WHERE transaction_id = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);

        while(results.next()) {
            Transaction transaction = mapTransactionFromResult(results);
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactionsByUser = new ArrayList<>();
        final String sql = "SELECT transfer_id, account_id_sender, account_id_receiver, amount_transferred_out, amount_received FROM transfer WHERE user_id = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            Transaction transaction = mapTransactionFromResult(results);
            transactionsByUser.add(transaction);
        }
        return transactionsByUser;
    }

    @Override
    public Transaction createTransaction(int senderUserId, int receiverUserId, BigDecimal amount) {
        return null;
    }


    private Transaction mapTransactionFromResult(SqlRowSet mapT) {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(mapT.getInt("transfer_id"));
        transaction.setSenderId(mapT.getInt("user_id_sender"));
        transaction.setReceiverId(mapT.getInt("user_id_receiver"));
        transaction.setAmountOut(mapT.getBigDecimal("amount_transferred_out"));
        transaction.setAmountIn(mapT.getBigDecimal("amount_received"));

        return transaction;
    }
}
