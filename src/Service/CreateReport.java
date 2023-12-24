package Service;

import util.StatusCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateReport {
    private static final String REPORT = "report.txt";

    public void createReport(String fileName, String transferInfo, StatusCode statusCode) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        String status = "";

        switch (statusCode) {
            case OK:
                status = statusCode.getMessage();
            case INCORRECT_AMOUNT:
                status = statusCode.getMessage();
            case INVALID_TO_NUMBER:
                status = statusCode.getMessage();
            case INVALID_FROM_NUMBER:
                status = statusCode.getMessage();
            case NON_EXISTENT_FROM_NUMBER:
                status = statusCode.getMessage();
            case NON_EXISTENT_TO_NUMBER:
                status = statusCode.getMessage();
            case NEGATIVE_AMOUNT:
                status = statusCode.getMessage();
        }

        String reportEntry = dateTime + " | " + fileName + " | " + transferInfo + " | " + status;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT, true))) {
            writer.write(reportEntry);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл-отчет: " + e.getMessage());
        }
    }
}
