package Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class UpdateAccount {

    private static final String ACCOUNTS = "D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt";

    public static void updateAccount(String accountNumber, int amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(ACCOUNTS))) {


            writer.write("");

            String accountEntry = accountNumber + " " + amount;
            writer.write(accountEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при обновлении файла с номерами счетов и суммами: " + e.getMessage());
        }
    }
}
