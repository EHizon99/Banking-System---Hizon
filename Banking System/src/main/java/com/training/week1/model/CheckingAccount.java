package com.training.week1.model;


public class CheckingAccount extends Account implements Transferable  {
    private static final double OVERDRAFT_LIMIT = 500.00;
    private int transactions;

    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder);
        this.accountType = AccountType.CHECKING;
        this.initialBalance = initialBalance;
    }

    @Override
    public String toString() {
        return "CheckingAccount" + "[" + getAccountNumber() + "," + "balance: " + initialBalance + "," + "transactions= " + transactions++ + "]";
    }

    @Override
    public void deposit (double amount) {
        if (amount > 0 ) {
            initialBalance += amount + OVERDRAFT_LIMIT;
            System.out.println("Deposited: " + amount);
            transactions++;
        }else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= initialBalance) {
            initialBalance -= amount;
            System.out.println("Withdrawn: " + amount);
            transactions++;
        } else if (amount > initialBalance + OVERDRAFT_LIMIT) {
            System.out.println("Withdraw: The amount of $ " + amount + " being withdrew already exceeded the limit of the $ "
                    + initialBalance + " balance.");
        } else if (amount == initialBalance + OVERDRAFT_LIMIT){
            initialBalance-= amount;
            System.out.println("Withdraw: Withdrew $ " + amount + " into  Checking account"
                    + ". The new remaining balance: $ " + initialBalance + " with overdraft limit applied.");
        } else {
            System.out.println("Invalid input");
        }
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