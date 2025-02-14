package com.training.week1.util;


import com.training.week1.exception.InsufficientFundsException;
import com.training.week1.model.Account;
import com.training.week1.model.CheckingAccount;
import com.training.week1.model.SavingsAccount;
import com.training.week1.service.AccountService;


public class Main {
    public static void main(String[] args) {
        TransactionLogger logger = new TransactionLogger();
        SavingsAccount savings= new SavingsAccount("SAV001","Andre", 1000);
        CheckingAccount checking = new CheckingAccount("CHK001", "Diana", 2000);
        AccountService.accounts.add(savings);
        AccountService.accounts.add(checking);
        System.out.println();

        System.out.println("Initial Account States: ");
        AccountService.initialAccountStates();
        try {
            savings.deposit(500.00);

            checking.withdraw(500.00);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        System.out.println("After Transactions:");
        AccountService.afterTransaction();
        System.out.println();

        System.out.println("After Monthly Fees: ");
        AccountService.afterMonthlyFees();
        System.out.println();

        System.out.println("Accounts Above 1500: ");
        AccountService.sortAccount();
        System.out.println();


        System.out.println("Accounts Sorted By Balance: ");
        AccountService.sortAccountByBalance();
        System.out.println();

        System.out.println("Total Balance: ");
        AccountService.totalBalance();
        System.out.println();

        System.out.println();
        TransactionLogger.transactionHistory();

        }







    }

