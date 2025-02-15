package com.training.week1.service;

import com.training.week1.exception.InsufficientFundsException;
import com.training.week1.model.Account;


public class TransactionService {

    public void transfer (Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException {
        synchronized (this) {
            if (fromAccount.getInitialBalance() < amount) {
                throw new InsufficientFundsException("Insufficient funds to transfer " + amount);
            }

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);

            fromAccount.addTransaction("Transferred " + amount + " to " + toAccount.getAccountNumber(), -amount);
            toAccount.addTransaction("Received " + amount + " from " + fromAccount.getAccountNumber(), amount);
        }
    }
}
