package Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class UpdateAccount {

    private static final String ACCOUNTS = "D:\\Courses\\MoneyTransferProgramm\\src\\accounts.txt";

    public static void updateAccount(String accountNumber, int amount){
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(ACCOUNTS), StandardOpenOption.APPEND)) {
            String accountEntry = accountNumber + " " + amount;
            writer.write(accountEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при обновлении файла с номерами счетов и суммами: " + e.getMessage());
        }
    }
}
