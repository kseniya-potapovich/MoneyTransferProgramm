package Service;

import Exceptions.InvalidTransferException;
import Models.Account;
import Models.Transfer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Parser {
    private static final String INPUT = "D:\\Courses\\MoneyTransferProgramm\\src\\input";
    private static final String ARCHIVE = "D:\\Courses\\MoneyTransferProgramm\\src\\archive";
    private static final String ACCOUNTS = "D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt";

    private static final String REPORT = "D:\\Courses\\MoneyTransferProgramm\\src\\report.txt";


    private Map<String, Double> accounts = new HashMap<>();
    private List<Transfer> transfers;

    public void readAccount() {
      /*  File input = new File(INPUT);
        File[] files = input.listFiles();

        if (files != null){
            for (File file : files){
                if (file.isFile() && file.getName().endsWith(".txt")){*/

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNTS))) {
            String line = "";
            String[] parts;
            while ((line = bufferedReader.readLine()) != null) {
                parts = line.split(" ");
                if (parts.length == 2) {
                    for (int j = 0; j < parts.length; j += 2) {
                        String number = parts[j];
                        Double amount = Double.valueOf(parts[j + 1]);
                        accounts.put(number, amount);
                    }
                }
            }
            System.out.println(accounts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parse() throws IOException {
        readAccount();
        File input = new File(INPUT);
        File[] files = input.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".txt")) {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                        String line = "";
                        String[] parts;
                        while ((line = bufferedReader.readLine()) != null) {
                            parts = line.split("\\|");
                            if (parts.length == 3) {
                                String fromAccountNumber = parts[0];
                                String toAccountNumber = parts[1];
                                Double amount = Double.valueOf(parts[2]);

                                if (!isValidAccountNumber(fromAccountNumber)) {
                                    CreateReport.createReport(file.getName(), line , fromAccountNumber, 3);
                                    continue;
                                }
                                if (!isValidAccountNumber(toAccountNumber)){
                                    CreateReport.createReport(file.getName(), line,toAccountNumber,3);
                                    continue;
                                }
                                Double a = accounts.get(fromAccountNumber);
                                if (a < amount) {
                                  CreateReport.createReport(file.getName(), line,"",2);
                                  continue;
                                }

                                accounts.put(fromAccountNumber, accounts.get(fromAccountNumber) - amount);
                                System.out.println(accounts);
                              Double r = accounts.get(toAccountNumber) + amount;
                                accounts.put(toAccountNumber,r);
                                System.out.println(accounts);


                                CreateReport.createReport(file.getName(), line,"",1);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ACCOUNTS))){
            bufferedWriter.write(accounts.toString());
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private static void moveFileToArchive(File file) {
        try {
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

   /* private Account getAccount(String accountNumber) {
        Account account = new Account(accountNumber);
        return account;
    }*/

    /*private List<File> findInput(){
        File input = new File(INPUT);
        List<File> inputFiles = new ArrayList<>();
        if (input.exists() && input.isDirectory()){
            for (File file : input.listFiles()){
                if (file.isFile() && file.getName().endsWith(".txt")){
                    inputFiles.add(file);
                }
            }
        }
        return inputFiles;
    }*/
}
