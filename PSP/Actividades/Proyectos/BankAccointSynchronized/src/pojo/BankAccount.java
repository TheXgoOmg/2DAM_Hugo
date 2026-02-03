package pojo;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void addMoney(int amount) {
        synchronized (this) {
            this.balance += amount;
            System.out.printf("Balance: %d\n", this.getBalance());
        }
    }

    public void takeOutMoney(int amount) {
        synchronized (this) {
            this.balance -= amount;
            System.out.printf("Balance: %d\n", this.getBalance());
        }
    }

    public int getBalance() {
        return balance;
    }
}
