import service.Parser;
import service.ReportPrinter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Parser parser = Parser.getInstance();
    static ReportPrinter printer = new ReportPrinter();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - вызов операции парсинга файлов перевода из input" +
                "\n2 - вызов операции вывода списка всех переводов из файла-отчета");
        System.out.println("Укажите желаемую операцию: ");
        int operation = scanner.nextInt();

        if (operation == 1) {
            parser.parse();
        } else if (operation == 2) {
            printer.print();
        } else {
            System.out.println("Неверно введена команда");
        }
    }
}
