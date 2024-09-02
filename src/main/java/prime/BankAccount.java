package prime;
class BankAccount {
    private static int nextAccountNumber = 1000;
    private int accountNumber;
    private String accountHolder;
    private String email;
    private String password;
    private double balance;

    public BankAccount(String accountHolder, String email, String password, double initialBalance) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolder = accountHolder;
        this.email = email;
        this.password = password;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getEmail() {
        return email;
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
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