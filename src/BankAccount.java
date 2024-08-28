public class BankAccount {
    private double balance;
    public BankAccount(){

    }
    public BankAccount(double balance){
        this.balance=balance;
    }
    public void deposit(double ammount){
        this.balance+=ammount;
    }
    public void withdraw(double ammount){
        this.balance-=ammount;
    }
    public void printBalacne(){
        System.out.println("Current balance = "+this.balance);
    }
    public void transferBalance(BankAccount target,double amount){
        if (amount <= this.balance) {
            this.withdraw(amount);
            target.deposit(amount);
            System.out.println("Transfer successful");
        } else {
            System.out.println("Insufficient funds for transfer");
        }
    }
}
