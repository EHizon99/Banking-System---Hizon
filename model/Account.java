package com.training.week1.model;


import java.time.LocalDateTime;
import java.time.Period;



public abstract class Account {
    // Private fields for sensitive data
    private final String accountNumber;
    private final String accountHolder;

    // Protected fields for subclass access
    protected AccountType accountType;
    protected LocalDateTime lastTransactionDate;
    protected double initialBalance;


    //Public constructor
    public Account(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.initialBalance = initialBalance;
        this.lastTransactionDate = LocalDateTime.now();

    }


    public void addTransaction(String description, double amount) {
        System.out.println("Transaction recorded: " + description + " Amount: " + amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    // Abstract methods for deposit and withdrawal
    public void deposit(double amount) {
        if (amount > 0) {
            initialBalance += amount;
            System.out.println("Deposited: " + amount);
        }else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= initialBalance) {
            initialBalance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Withdrawal failed: Insufficient funds or invalid amount.");
        }
    }

}

