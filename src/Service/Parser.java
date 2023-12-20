package Service;

import Exceptions.InvalidTransferException;
import Models.Account;
import Models.Transfer;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser  {
    private static final String INPUT = "D:\\Courses\\MoneyTransferProgramm\\src\\input";
    private static final String ARCHIVE = "D:\\Courses\\MoneyTransferProgramm\\src\\archive";


    private List<Account> accounts;

    public Parser() {
        accounts = new ArrayList<>();
    }


    public void parse() {
        File input = new File(INPUT);
        File[] files = input.listFiles();

        if (files != null){
            for (File file : files){
                if (file.isFile() && file.getName().endsWith(".txt")){
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()){
                            String line = scanner.nextLine();
                            String[] parts = line.split("\\|");
                            if (parts.length == 3){
                                String fromAccountNumber = parts[0];
                                String toAccountNumber = parts[1];
                                int amount = Integer.parseInt(parts[2]);

                                Account fromAccount = getAccount(fromAccountNumber);
                                Account toAccount = getAccount(toAccountNumber);

                                if (fromAccount.getAmount() < amount){
                                    CreateReport.createReport(file.getName(), line,false);
                                }

                                if (!isValidAccountNumber(fromAccountNumber) || !isValidAccountNumber(toAccountNumber)){
                                    CreateReport.createReport(file.getName(), line,false);
                                    continue;
                                }
                                if (amount <= 0) {
                                    CreateReport.createReport(file.getName(), line, false);
                                    continue;
                                }

                                UpdateAccount.updateAccount(fromAccountNumber, -amount);
                                UpdateAccount.updateAccount(toAccountNumber, amount);

                                CreateReport.createReport(file.getName(), line, true);
                            }else {
                                CreateReport.createReport(file.getName(), line,false);
                            }
                        }
                        scanner.close();
                        moveFileToArchive(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    private static void moveFileToArchive(File file)  {
        try{
            Path sourcePath = file.toPath();
            Path targetPath = Path.of(ARCHIVE, file.getName());
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isValidAccountNumber(String accountNumber) {
        String regex = "\\d{5}-\\d{5}";
        return Pattern.matches(regex, accountNumber);
    }

    private Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
