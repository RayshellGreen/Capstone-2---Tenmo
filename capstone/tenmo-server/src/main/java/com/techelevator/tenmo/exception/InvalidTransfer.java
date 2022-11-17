package com.techelevator.tenmo.exception;

public class InvalidTransfer extends Throwable {
    public InvalidTransfer() {
        System.out.println("You cannot send money to yourself!!");
    }
}
