package com.techelevator.tenmo.exception;

public class InvalidTransfer extends Throwable {
    public InvalidTransfer() {
        System.out.println("Transfer could not be complete. There are insufficient funds in your account.");
    }
}
