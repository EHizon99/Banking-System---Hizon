package com.training.week1.service;

import com.training.week1.model.Account;
import com.training.week1.model.AccountType;
import com.training.week1.model.CheckingAccount;
import com.training.week1.model.SavingsAccount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountService  {
    SavingsAccount save = new SavingsAccount("","",1000);
    CheckingAccount check = new CheckingAccount("","",2000);

    public static List<Account> accounts = new ArrayList<>();


    public Account createAccount(String accountNumber, String accountHolder, AccountType type, double initialBalance) {
        Account account = null;

        if (type == AccountType.SAVINGS) {
            account = new SavingsAccount(accountNumber, accountHolder, initialBalance);
        } else if (type == AccountType.CHECKING) {
            account = new CheckingAccount(accountNumber, accountHolder, initialBalance);
        }
        if (account != null) {
            accounts.add(account); // Store the created account in the list
        }
        return account; // Return the created account
    }


    public static void sortAccount() {
        List<Account> filteredAccounts = accounts.stream()
                .filter(account -> account.getInitialBalance() > 1500)
                .collect(Collectors.toList());

        // Print filtered accounts
        filteredAccounts.forEach(System.out::println);

    }
    public static void initialAccountStates(){
        accounts.forEach(System.out::println);
        System.out.println();
    }

    public static void afterTransaction (){

        accounts.forEach(System.out::println);
        System.out.println();
    }

    public static void afterMonthlyFees(){
        accounts.forEach(System.out::println);
        System.out.println();
    }
    public static void sortAccountByBalance () {
        accounts.sort(Comparator.comparingDouble(Account::getInitialBalance));
        accounts.forEach(System.out::println);
        System.out.println();
    }

    public static void totalBalance () {
        double totalBalance = accounts.stream().mapToDouble(Account::getInitialBalance).sum();
        System.out.printf("Total Balance: $%2.5f%n" , totalBalance);
        System.out.println();
    }

    public static void lookupAccount () {
        String lookId = "SAV001";
        Optional<Account> accountLookup = accounts.stream().filter(account -> account.getAccountNumber()
                .equals(lookId)).findFirst();
        accountLookup.ifPresent(System.out::println);
        System.out.println();

    }



}

