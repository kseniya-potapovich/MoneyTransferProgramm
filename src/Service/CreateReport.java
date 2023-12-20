package Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateReport {
    public static  void  createReport(String fileName, String transferInfo, boolean isSuccess){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        String status ;
        if (isSuccess == true){
            status = "успешно обработан";
        } else{
            status = "ошибка во время обработки, неверная сумма перевода";
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
