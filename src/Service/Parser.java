package Service;

import Models.Transfer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Parser {
    private static final String INPUT = "D:\\Courses\\MoneyTransferProgramm\\src\\input";
    private static final String ARCHIVE = "D:\\Courses\\MoneyTransferProgramm\\src\\archive";
    private static final String ACCOUNTS = "D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt";
    private static final String REPORT = "D:\\Courses\\MoneyTransferProgramm\\src\\report.txt";

    private Map<String, Double> accounts = new HashMap<>();

    public void readAccount() {
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

                                if (!accounts.containsKey(fromAccountNumber)) {
                                    CreateReport.createReport(file.getName(), line, fromAccountNumber, 4);
                                    continue;
                                }
                                if (!accounts.containsKey(toAccountNumber)) {
                                    CreateReport.createReport(file.getName(), line, toAccountNumber, 4);
                                    continue;
                                }
                                if (amount <= 0) {
                                    CreateReport.createReport(file.getName(), line, "", 2);
                                    continue;
                                }
                                if (!isValidAccountNumber(fromAccountNumber)) {
                                    CreateReport.createReport(file.getName(), line, fromAccountNumber, 3);
                                    continue;
                                }
                                if (!isValidAccountNumber(toAccountNumber)) {
                                    CreateReport.createReport(file.getName(), line, toAccountNumber, 3);
                                    continue;
                                }
                                if (accounts.get(fromAccountNumber) < amount) {
                                    CreateReport.createReport(file.getName(), line, "", 2);
                                    continue;
                                }

                                accounts.put(fromAccountNumber, accounts.get(fromAccountNumber) - amount);
                                accounts.put(toAccountNumber, accounts.get(toAccountNumber) + amount);

                                CreateReport.createReport(file.getName(), line, "", 1);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                moveFileToArchive(file);
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ACCOUNTS))) {
                Set<String> numbers = accounts.keySet();
                for (String number : numbers) {
                    bufferedWriter.write(number + " " + accounts.get(number) + "\n");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
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
}
