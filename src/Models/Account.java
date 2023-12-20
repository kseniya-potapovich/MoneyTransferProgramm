package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Account {
    private String number;
    private int amount;

    public Account(String number, int amount) {
        this.number = number;
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
