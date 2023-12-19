package Service;

import Models.Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountReader {
    public List<Account> readAccounts(String fileName) {
        List<Account> accounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(" ");

                if (accountData.length == 2) {
                    String accountNumber = accountData[0].trim();
                    int balance = Integer.parseInt(accountData[1].trim());

                    Account account = new Account(accountNumber, balance);
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return accounts;
    }
}
