package service;

import util.StatusCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportCreator {
    private static final String REPORT = "report.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void createReport(String fileName, String transferInfo, StatusCode statusCode) {
        String dateTime = LocalDateTime.now().format(FORMATTER);
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT,true))) {
            writer.write(reportEntry);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл-отчет: " + e.getMessage());
        }
    }
}
