package Service;

import Models.Account;
import Models.Transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser {
    private AccountReader accountReader = new AccountReader();
    private List<Account> accounts = accountReader.readAccounts("D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt");
    private TransferReader transferReader = new TransferReader();
    private List<Transfer> transfer = transferReader.parseTransfersFromDirectory("D:\\Courses\\MoneyTransferProgramm\\src\\input");

    public void parse() {
        File input = new File("D:\\Courses\\MoneyTransferProgramm\\src\\input");
        File[] files = input.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Нет файлов для парсинга.");
            return;
        }


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

                    if (fromAccount == null || toAccount == null) {
                        System.out.println("Account isn't exists " + line);
                        continue;
                    }
                    if (fromAccount.getBalance() < toAccount.getBalance()) {
                        System.out.println("Insufficient funds " + line);
                        continue;
                    }

                    fromAccount.setBalance(fromAccount.getBalance() - amount);
                    toAccount.setBalance(toAccount.getBalance() + amount);

//                    archiveFile(file);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        updateFile();
    }

    private boolean isValidAccount(String accountNumber) {
        return accountNumber.matches("\\d{5}-\\d{5}");
    }

    private Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private void updateFile() {
        try (FileWriter fileWriter = new FileWriter("D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt")) {
            for (Account account : accounts) {
                fileWriter.write(account.getNumber() + " " + account.getBalance());
                fileWriter.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

   /* private void archiveFile(File file) {
        try {
            Path source = file.toPath();
            Path destination = Path.of("archive", file.getName());
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
