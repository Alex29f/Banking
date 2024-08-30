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
            System.out.println("6. List All Accounts");
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
                case 6 -> listAccounts(accounts);
                case 0 -> System.out.println("Thank you for using our services!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private static void createAccount(Scanner sc, ArrayList<BankAccount> accounts) {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = sc.nextDouble();
        BankAccount newAccount = new BankAccount(name, initialBalance);
        accounts.add(newAccount);
        System.out.println("Account created successfully! Account number: " + newAccount.getAccountNumber());
    }

    private static BankAccount findAccount(Scanner sc, ArrayList<BankAccount> accounts) {
        System.out.print("Enter account number: ");
        int accountNumber = sc.nextInt();
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("Account not found.");
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
        System.out.println("Destination account:");
        BankAccount destAccount = findAccount(sc, accounts);

        if (sourceAccount != null && destAccount != null) {
            System.out.print("Enter transfer amount: ");
            double amount = sc.nextDouble();
            sourceAccount.transferBalance(destAccount, amount);
            System.out.println("Source account balance after transfer:");
            sourceAccount.printBalance();
            System.out.println("Destination account balance after transfer:");
            destAccount.printBalance();
        }
    }

    private static void listAccounts(ArrayList<BankAccount> accounts) {
        System.out.println("\nAll Bank Accounts:");
        for (BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() +
                    ", Holder: " + account.getAccountHolder() +
                    ", Balance: " + account.getBalance());
        }
    }
}