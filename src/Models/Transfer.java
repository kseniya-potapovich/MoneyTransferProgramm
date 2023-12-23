package Models;

import java.time.LocalDateTime;

public class Transfer {
    private String fromAccountNumber;
    private String toAccountNumber;
    private int amount;

    public Transfer(String fromAccountNumber, String toAccountNumber, int amount) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public int getAmount() {
        return amount;
    }


}
