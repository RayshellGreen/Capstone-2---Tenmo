package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class transactionDTO {

    int senderUserId;
    int receiverUserId;
    BigDecimal amount;

    public transactionDTO(int senderUserId, int receiverUserId, BigDecimal amount) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.amount = amount;
    }

    public transactionDTO() {}

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
