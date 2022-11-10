package com.techelevator.tenmo.exception;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() {
        System.out.println("Transfer could not be complete. There are insufficient funds in your account.");
    }
}
