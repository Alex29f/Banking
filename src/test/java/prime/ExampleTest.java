package prime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

class ExampleTest {

    private BankAccount account;
    private ArrayList<BankAccount> accounts;

    @BeforeEach
    void setUp() {
        account = new BankAccount("John Doe", "john@example.com", "password123", 1000.0);
        accounts = new ArrayList<>();
        accounts.add(account);
    }
    @Test
    void testCreateAccount() {
        String input = "Jane Doe\njane@example.com\npassword456\n500\n";
        Scanner scanner = new Scanner(input);
        Main.createAccount(scanner, accounts);

        Assertions.assertEquals(2, accounts.size());
        BankAccount newAccount = accounts.get(1);
        Assertions.assertEquals("Jane Doe", newAccount.getAccountHolder());
        Assertions.assertEquals("jane@example.com", newAccount.getEmail());
        Assertions.assertEquals(500.0, newAccount.getBalance(), 0.001);
    }

    @Test
    void testFindAccountWithCorrectPassword() {
        String input = account.getAccountNumber() + "\npassword123\n";
        Scanner scanner = new Scanner(input);
        BankAccount foundAccount = Main.findAccount(scanner, accounts);

        Assertions.assertNotNull(foundAccount);
        Assertions.assertEquals(account, foundAccount);
    }

    @Test
    void testFindAccountWithIncorrectPassword() {
        String input = account.getAccountNumber() + "\nwrongpassword\n";
        Scanner scanner = new Scanner(input);
        BankAccount foundAccount = Main.findAccount(scanner, accounts);

        Assertions.assertNull(foundAccount);
    }

    @Test
    void testDeposit() {
        account.deposit(500.0);
        Assertions.assertEquals(1500.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawSufficientFunds() {
        account.withdraw(500.0);
        Assertions.assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawInsufficientFunds() {
        account.withdraw(1500.0);
        Assertions.assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    void testTransferSufficientFunds() {
        BankAccount targetAccount = new BankAccount("Jane Doe", "jane@example.com", "password456", 500.0);
        account.transferBalance(targetAccount, 500.0);
        Assertions.assertEquals(500.0, account.getBalance(), 0.001);
        Assertions.assertEquals(1000.0, targetAccount.getBalance(), 0.001);
    }

    @Test
    void testTransferInsufficientFunds() {
        BankAccount targetAccount = new BankAccount("Jane Doe", "jane@example.com", "password456", 500.0);
        account.transferBalance(targetAccount, 1500.0);
        Assertions.assertEquals(1000.0, account.getBalance(), 0.001);
        Assertions.assertEquals(500.0, targetAccount.getBalance(), 0.001);
    }
}