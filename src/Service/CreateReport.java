package Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateReport {
    public static  void  createReport(String fileName, String transferInfo, String invalidNumber, int success){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        String status = "";

        switch (success){
            case 1:
                status = "успешно обработан";
                break;
            case 2:
                status = "ошибка во время обработки, неверная сумма перевода";
                break;
            case 3:
                status = "ошибка во время обработки, невалидный номер счета " + invalidNumber;
        }

        String reportEntry = dateTime + " | " + fileName + " | " + transferInfo + " | " + status;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt", true))) {
            writer.write(reportEntry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл-отчет: " + e.getMessage());
        }
    }
}
