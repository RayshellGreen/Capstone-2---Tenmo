package com.techelevator.tenmo.exception;

public class InvalidAmount extends Throwable {
    public InvalidAmount() {
        System.out.println("Please enter an amount greater than zero.");
    }
}
