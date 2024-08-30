package prime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount();
        int choice =-1;
        BankAccount target = new BankAccount(0);
        while (choice != 0) {
            System.out.println("\n--- Bank Account Management ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer (to another account)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    createAccount(sc, account);
                    account.printBalacne();
                }
                case 2 -> {
                    deposit(sc, account);
                    account.printBalacne();
                }
                case 3 -> {
                    withdraw(sc, account);
                    account.printBalacne();
                }
                case 4 -> account.printBalacne();
                case 5 -> transfer(sc, account);
                case 0 -> System.out.println("Thank you for using our services!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private static void createAccount(Scanner sc, BankAccount account) {
        System.out.print("Enter initial balance: ");
        double initialBalance = sc.nextDouble();
        account.deposit(initialBalance);
        System.out.println("Account created successfully!");
    }

    private static void deposit(Scanner sc, BankAccount account) {
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();
        account.deposit(amount);
        System.out.println("Deposit successful!");
    }



    private static void withdraw(Scanner sc, BankAccount account) {
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();
        account.withdraw(amount);
    }

    private static void transfer(Scanner sc, BankAccount account) {
        System.out.println("Note: This will create a new account to transfer to.");
        System.out.print("Enter transfer amount: ");
        double amount = sc.nextDouble();
        BankAccount recipient = new BankAccount();
        account.transferBalance(recipient, amount);
        System.out.println("Recipient's balance after transfer:");
        recipient.printBalacne();
    }
}