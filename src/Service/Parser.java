package Service;

import Models.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<Account> accounts;

    public Parser() {
        accounts = new ArrayList<>();
    }

    public void parse() {
        File input = new File("D:\\Courses\\MoneyTransferProgramm\\src\\input");
        File[] files = input.listFiles();

        for (File file : files) {
            if (!file.getName().endsWith(".txt")) {
                continue;
            }
            try (FileReader fileReader = new FileReader(file);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line = null;
                String[] fields = new String[0];
                while ((line = bufferedReader.readLine()) != null) {
                    fields = line.split("\\|");
                }

                String numberFromAccount = fields[0].trim();
                String numberToAccount = fields[1].trim();
                int amount = Integer.parseInt(fields[2].trim());

                if (!isValidAccount(numberFromAccount) || !isValidAccount(numberToAccount)) {
                    System.out.println("Number of account isn't valid " + line);
                    continue;
                }
                if (amount <= 0) {
                    System.out.println("Insufficient funds " + line);
                    continue;
                }

                Account fromAccount = getAccount(numberFromAccount);
                Account toAccount = getAccount(numberToAccount);

                if (fromAccount == null || toAccount == null){
                    System.out.println("Account isn't exists " + line);
                    continue;
                }
                if (fromAccount.getBalance() < toAccount.getBalance()){
                    System.out.println("Insufficient funds " + line);
                    continue;
                }

                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);

                updateFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    private boolean isValidAccount(String accontNumber) {
        return accontNumber.matches("\\d{5}-\\d{5}");
    }

    private Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private void updateFile(){
        try(FileWriter fileWriter = new FileWriter("D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt")){
            for (Account account : accounts){
                fileWriter.write(account.getNumber() + " " + account.getBalance());
                fileWriter.flush();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
