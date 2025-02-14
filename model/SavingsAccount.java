package com.training.week1.model;

import java.time.LocalDateTime;

public class SavingsAccount extends Account implements Transferable {
    private static final double MINIMUM_BALANCE = 100.00;
    private static final double INTEREST_RATE = 0.025;


    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder);
        this.accountType = AccountType.SAVINGS;
        this.initialBalance = initialBalance;
    }



    @Override
    public void deposit(double amount) {
        if (amount > 0 ) {
            initialBalance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        initialBalance = initialBalance + (amount);

        lastTransactionDate = LocalDateTime.now();
    }

    @Override
    public void withdraw(double amount) {
        if (amount >= initialBalance ) {
            System.out.println("Withdrawal failed: Insufficient funds or invalid amount.");
        } else {
            System.out.println("Invalid withdraw amount.");
        }
        initialBalance-= amount;
        lastTransactionDate = LocalDateTime.now();
        System.out.println(lastTransactionDate);
        System.out.println("Withdrawn: " + initialBalance);

    }

    public void addInterest(){
        double interest = initialBalance * INTEREST_RATE /12 ; // Monthly Interest
        initialBalance += interest;
        System.out.println("Interest of " + interest);

    }


    @Override
    public String toString() {
       return "SavingsAccount" + "[" + getAccountNumber() + "," + "balance: " + initialBalance + "," + "Interest Rate: " + INTEREST_RATE*100
               + "% ]" ;
    }



    @Override
    public void transfer(Account destination, double amount) {
        if (destination == null || amount<= 0) {
            throw new IllegalArgumentException("Invalid destination account or amount");
        }
        try {
            withdraw(amount);
            destination.deposit(amount);
        } catch (Exception e) {
            // If deposit fails, attempt to rollback
            try {
                deposit(amount);
            } catch (Exception rollbackException) {
                // Log or handle rollback failure if necessary
                throw new IllegalStateException("Transfer failed, and rollback unsuccessful", rollbackException);
            }
            throw new IllegalStateException("Transfer failed", e);
        }
    }
    
}