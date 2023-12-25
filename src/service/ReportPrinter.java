package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ReportPrinter {
    private static final String REPORT = "report.txt";

    public void print() {
        String printReport = getReport();
        System.out.println(printReport);
    }

    /*public void printOnDate(LocalDate startDate, LocalDate endDate) {
        List<String> reportLines = new ArrayList<>();
        try (Scanner scanner = new Scanner(REPORT)) {
            String line = "";
            String[] parts;
            while (scanner.hasNextLine()) {
                parts = line.split("\\|");
                if (parts.length == 4) {
                    LocalDate formatter = LocalDate.parse(parts[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    if (!formatter.isAfter(startDate) && !formatter.isBefore(endDate)) {
                        reportLines.add(line);
                    }
                }
            }
        }

        if (reportLines.isEmpty()) {
            System.out.println("На указанные даты записей не найдено.");
        } else {
            for (String line : reportLines) {
                System.out.println(line);
            }
        }
    }*/

    private String getReport() {
        StringBuilder report = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(REPORT))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                report.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла отчета " + e.getMessage());
        }
        return report.toString();
    }
}
