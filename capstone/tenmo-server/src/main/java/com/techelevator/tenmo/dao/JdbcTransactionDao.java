package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;

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

        final String sql = "SELECT transfer_id, user_id_sender, user_id_receiver, amount FROM transfer; ";

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
        final String sql = "SELECT transfer_id, user_id_sender, user_id_receiver, amount FROM transfer WHERE transaction_id = ?; ";

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
        final String sql = "SELECT transfer_id, user_id_sender, user_id_receiver, amount FROM transfer WHERE user_id_sender = ? OR user_id_receiver = ?; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            Transaction transaction = mapTransactionFromResult(results);
            transactionsByUser.add(transaction);
        }
        return transactionsByUser;
    }

    @Override
    public void sendFunds(Transaction transaction) {
        final String sql = "INSERT INTO transfer (user_id_sender, user_id_receiver, amount)" +
                "VALUES (?, ?, ?); ";
        jdbcTemplate.update(sql, transaction.getSenderId(), transaction.getReceiverId(), transaction.getAmount());


    }

    private Transaction mapTransactionFromResult(SqlRowSet mapT) {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(mapT.getInt("transfer_id"));
        transaction.setSenderId(mapT.getInt("user_id_sender"));
        transaction.setReceiverId(mapT.getInt("user_id_receiver"));
        transaction.setAmount(mapT.getBigDecimal("amount"));

        return transaction;
    }

}
