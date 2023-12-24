package Service;

import util.StatusCode;

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
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Parser {
    private static final String INPUT = "D:\\Courses\\MoneyTransferProgramm\\src\\input";
    private static final String ARCHIVE = "D:\\Courses\\MoneyTransferProgramm\\src\\archive";
    private static final String ACCOUNTS = "D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt";
    private static final String regex = "\\d{5}-\\d{5}";

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
            System.out.println("Ошибка при чтении файла с номерами счетов " + e.getMessage());
        }
    }

    public void parse() {
        readAccount();
        File input = new File(INPUT);
        File[] files = input.listFiles();
        CreateReport createReport = new CreateReport();

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

                                StatusCode statusCode = validate(fromAccountNumber, toAccountNumber, amount);

                                if (statusCode == StatusCode.OK) {
                                    accounts.put(fromAccountNumber, accounts.get(fromAccountNumber) - amount);
                                    accounts.put(toAccountNumber, accounts.get(toAccountNumber) + amount);
                                }
                                createReport.createReport(file.getName(), line, statusCode);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Ошибка при парсинге папки input " + e.getMessage());
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
                System.out.println("Ошибка при обновлении файла с номерами счетов " + e.getMessage());
            }
        }
    }

    private void moveFileToArchive(File file) {
        try {
            Path sourcePath = file.toPath();
            Path targetPath = Path.of(ARCHIVE, file.getName());
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Ошибка при архифировании файлов " + e.getMessage());
        }
    }

    private boolean isValidAccountNumber(String accountNumber) {
        return Pattern.matches(regex, accountNumber);
    }

    private StatusCode validate(String fromAccountNumber, String toAccountNumber, Double amount) {
        if (!isValidAccountNumber(fromAccountNumber)) {
            return StatusCode.INVALID_FROM_NUMBER;
        }
        if (!isValidAccountNumber(toAccountNumber)) {
            return StatusCode.INVALID_TO_NUMBER;
        }
        if (!accounts.containsKey(fromAccountNumber)) {
            return StatusCode.NON_EXISTENT_FROM_NUMBER;
        }
        if (!accounts.containsKey(toAccountNumber)) {
            return StatusCode.NON_EXISTENT_TO_NUMBER;
        }
        if (accounts.get(fromAccountNumber) < amount) {
            return StatusCode.NEGATIVE_AMOUNT;
        }
        if (amount <= 0) {
            return StatusCode.INCORRECT_AMOUNT;
        }
        return StatusCode.OK;
    }
}
