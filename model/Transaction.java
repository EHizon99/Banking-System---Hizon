package com.training.week1.model;


import java.time.LocalDateTime;

public class Transaction {

    private final String transactionType;
    private final double amount;
    private final LocalDateTime timestamp;


    public Transaction(String transactionType, double amount) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();

    }

    public String transactionType() {
        return transactionType;
    }


    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
