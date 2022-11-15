package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transaction {

    private int transactionId;
    private int senderId; //sender account_id
    private int receiverId; //receiver account_id
    private BigDecimal amount;

    public Transaction(int transactionId, int senderId, int receiverId,
                       BigDecimal amount) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public Transaction() {

    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
