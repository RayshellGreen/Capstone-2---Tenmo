package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao  implements  AccountDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Account> findAll() {
        List<Account> allAccounts = new ArrayList<>();
       final String sql = "SELECT account_id, user_id, balance FROM account;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
       while (results.next()) {
           Account account= mapAccountFromResult(results);
           allAccounts.add(account);
       }
        return allAccounts;
    }

    @Override
    public Account findAccountByUserId(int userId) {
        Account findAccount  = new Account();
        final String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id =?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            Account account = mapAccountFromResult(results);
            findAccount= mapAccountFromResult(results);

        }
        return findAccount;
    }

    @Override
    public BigDecimal getBalanceByUserId(int userId) {

        final String sql = "SELECT balance FROM account WHERE user_id = ?; ";

        BigDecimal accountBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);

        return accountBalance;

    }


    private Account mapAccountFromResult(SqlRowSet mapAC) {
        Account account = new Account();

        account.setAccountId(mapAC.getInt("account_id"));
        account.setUserId(mapAC.getInt("user_id"));
        account.setBalance(mapAC.getBigDecimal("balance"));
        return account;
    }

}
