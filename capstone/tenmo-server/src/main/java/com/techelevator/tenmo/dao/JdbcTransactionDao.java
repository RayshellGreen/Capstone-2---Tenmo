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

    @Override //TODO does this work
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

    @Override //TODO does this work? Redo SQL statement?
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactionsByUser = new ArrayList<>();
        final String sql = "SELECT transfer_id, user_id_sender, user_id_receiver, amount FROM transfer WHERE user_id_sender = ?; "; //or(and) user_id_receiver??

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            Transaction transaction = mapTransactionFromResult(results);
            transactionsByUser.add(transaction);
        }
        return transactionsByUser;
    }


    @Override
    public void sendFunds(Transaction transaction) {
        final String sql = "INSERT INTO transfer (account_id_sender, account_id_receiver, amount)" +
                "VALUES ((select account_id from account where user_id = ?), (select account_id from account where user_id = ?), ?); ";
        jdbcTemplate.update(sql, transaction.getSenderId(), transaction.getReceiverId(), transaction.getAmount());


    }


//    public void sendFunds(int senderId, int receiverId, BigDecimal amount) {
//         //replaced Transaction transaction parameter
//    }
//        String sql = "INSERT INTO transfer (user_id_sender, user_id_receiver, amount)" +
//                "VALUES (?, ?, ?); " ;
//
//        try {
//            String sql1 = "UPDATE account SET balance = (balance + ?) WHERE user_id = ?; ";
//            jdbcTemplate.update(sql, amount, receiverId); //replaced transaction.get..
//
//            String sql2 = "UPDATE account SET balance = (balance - ?) WHERE user_id = ?; ";
//
//            jdbcTemplate.update(sql, amount, senderId);
//        }catch (ResourceAccessException e) {
//            System.out.println(e.getMessage());
//        }
//    }


//    @Override //TODO may need to uncomment
//    public Transaction createTransaction(Transaction transaction) {
////        Transaction transaction = new Transaction();
//        final String sql = "INSERT INTO transfer (user_id_sender, user_id_receiver, amount)" +
//                "VALUES (?, ?, ?); ";
//        jdbcTemplate.update(sql, transaction.getSenderId(), transaction.getReceiverId(), transaction.getAmount());
//        return transaction;
//    }


//    public BigDecimal sendFunds(int senderUserId, int receiverUserId, BigDecimal amount) {
//
//        BigDecimal currentBalance =
//                Int finalBalance
//        currentBalance - transferAmount = finalBalance
//        if transfer amount is <= 0, that isn't allowed
//        final balance is the new value of the sender's account balance
//        if the final balance < 0, I don't have enough money throw error
//
//
//    }


    private Transaction mapTransactionFromResult(SqlRowSet mapT) {
        Transaction transaction = new Transaction();

        transaction.setTransactionId(mapT.getInt("transfer_id"));
        transaction.setSenderId(mapT.getInt("user_id_sender"));
        transaction.setReceiverId(mapT.getInt("user_id_receiver"));
        transaction.setAmount(mapT.getBigDecimal("amount"));

        return transaction;
    }

}
