package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Account {
    private String number;
    private int balance;

    public Account(String number, int balance) {
        this.number = number;
        this.balance = balance;
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

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }
}
