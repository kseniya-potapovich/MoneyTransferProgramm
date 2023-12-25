package models;

public class Account {
    private String number;
    private double amount;

    public Account(String number, double amount) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public double setAmount(double amount) {
        return amount;
    }
}
