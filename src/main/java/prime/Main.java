package prime;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- Bank Account Management ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount(sc, accounts);
                case 2 -> deposit(sc, accounts);
                case 3 -> withdraw(sc, accounts);
                case 4 -> checkBalance(sc, accounts);
                case 5 -> transfer(sc, accounts);
                case 0 -> System.out.println("Thank you for using our services!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    public static void createAccount(Scanner sc, ArrayList<BankAccount> accounts) {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = sc.nextDouble();
        sc.nextLine(); // Consume newline

        BankAccount newAccount = new BankAccount(name, email, password, initialBalance);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Account number: " + newAccount.getAccountNumber());
    }

    public static BankAccount findAccount(Scanner sc, ArrayList<BankAccount> accounts) {
        System.out.print("Enter account number: ");
        int accountNumber = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.verifyPassword(password)) {
                return account;
            }
        }
        System.out.println("Account not found or incorrect password.");
        return null;
    }

    private static void deposit(Scanner sc, ArrayList<BankAccount> accounts) {
        BankAccount account = findAccount(sc, accounts);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = sc.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful!");
            account.printBalance();
        }
    }

    private static void withdraw(Scanner sc, ArrayList<BankAccount> accounts) {
        BankAccount account = findAccount(sc, accounts);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();
            account.withdraw(amount);
            account.printBalance();
        }
    }

    private static void checkBalance(Scanner sc, ArrayList<BankAccount> accounts) {
        BankAccount account = findAccount(sc, accounts);
        if (account != null) {
            account.printBalance();
        }
    }

    private static void transfer(Scanner sc, ArrayList<BankAccount> accounts) {
        System.out.println("Source account:");
        BankAccount sourceAccount = findAccount(sc, accounts);
        if (sourceAccount == null) return;

        System.out.println("Destination account:");
        System.out.print("Enter destination account number: ");
        int destAccountNumber = sc.nextInt();
        sc.nextLine(); // Consume newline

        BankAccount destAccount = null;
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == destAccountNumber) {
                destAccount = account;
                break;
            }
        }

        if (destAccount == null) {
            System.out.println("Destination account not found.");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Consume newline

        sourceAccount.transferBalance(destAccount, amount);
        System.out.println("Source account balance after transfer:");
        sourceAccount.printBalance();
    }
}