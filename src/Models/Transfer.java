package Models;

public class Transfer {
    private String numberFromAccount;
    private String numberToAccount;
    private int amount;

    public Transfer(String numberFromAccount, String numberToAccount, int amount) {
        this.numberFromAccount = numberFromAccount;
        this.numberToAccount = numberToAccount;
        this.amount = amount;
    }

    public String getNumberFromAccount() {
        return numberFromAccount;
    }

    public String getNumberToAccount() {
        return numberToAccount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "numberFromAccount='" + numberFromAccount + '\'' +
                ", numberToAccount='" + numberToAccount + '\'' +
                ", amount=" + amount +
                '}';
    }
}
