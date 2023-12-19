package Models;

public class Account {
    private String number;
    private int balance;

    public Account(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
