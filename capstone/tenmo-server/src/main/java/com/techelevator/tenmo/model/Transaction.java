package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transaction {

    private int transactionId;
    private int senderId;
    private int receiverId;
    private BigDecimal amountOut;
    private BigDecimal amountIn;

    public Transaction(int transactionId, int senderId, int receiverId,
                       BigDecimal amountOut, BigDecimal amountIn) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amountOut = amountOut;
        this.amountIn = amountIn;
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

    public BigDecimal getAmountOut() {
        return amountOut;
    }

    public BigDecimal getAmountIn() {
        return amountIn;
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

    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }

    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }
}
