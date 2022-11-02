package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao  implements  AccountDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}


    @Override
    public Account create(int user_id) {
        return null;
    }

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
    public Account findAccountByUserId(int id) {
        Account findAccount  = new Account();
        final String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id =?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            Account account = mapAccountFromResult(results);
            findAccount= mapAccountFromResult(results);

        }
        return findAccount;
    }

    @Override
    public Account getBalanceByUserId(int id) {
        Account balanceOfAccount = new Account();
        final String sql = "";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);  //TODO should this be query for object since we only want the balance? Not sure how to finish this
        while (results.next()) {
            Account account = mapAccountFromResult(results);

        }
        return null;
    }

    //TODO
//    @Override
//    public Account getBalanceByAccountId(long id) {
//        return null;
//    }
//
//    @Override
//    public Account getAccountId(long id) {
//        return null;
//    }
    private Account mapAccountFromResult(SqlRowSet mapAC) {
        Account account = new Account();

        account.setAccountId(mapAC.getInt("account_id"));
        account.setUserId(mapAC.getInt("user_id"));
        account.setBalance(mapAC.getBigDecimal("balance"));
        return account;
    }

}
