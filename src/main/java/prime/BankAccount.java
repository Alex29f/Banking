package prime;
class BankAccount {
    private static int nextAccountNumber = 1000;
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal successful");
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void printBalance() {
        System.out.println("Current balance for account " + accountNumber + ": " + this.balance);
    }

    public void transferBalance(BankAccount target, double amount) {
        if (amount <= this.balance) {
            this.withdraw(amount);
            target.deposit(amount);
            System.out.println("Transfer successful");
        } else {
            System.out.println("Insufficient funds for transfer");
        }
    }
}
