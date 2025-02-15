package com.training.week1.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;


public class TransactionLogger {
    private static final Path transactionLogs = Path.of("transactions.txt");

    public void saveTransaction(String accountNumber, String transactionType ,String accountName, double amount) {
        try {
            String transaction = String.format("Time: [%s] Account Number: %s |Account Type: %s| AccountName: %s| Amount: $ %.2f\n",
                    LocalDateTime.now(), accountNumber,transactionType, accountName, amount);

            Files.writeString(transactionLogs, transaction,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            System.out.println("Input Success!!");
        } catch (IOException e) {
            System.out.println("Error writing transaction log" + e.getMessage());
        }
    }

    public static void transactionHistory() {
        try {
            String transaction = Files.readString(transactionLogs);
            System.out.println("Transaction History: \n-----------------------------------------\n" + transaction);

        } catch (IOException e) {
            System.out.println("Error reading transaction log" + e.getMessage());
        }
    }
}



