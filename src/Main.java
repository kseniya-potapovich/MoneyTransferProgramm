import Service.Parser;
import Service.WriteReport;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Parser parser = new Parser();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - вызов операции парсинга файлов перевода из input" +
                "\n2 - вызов операции вывода списка всех переводов из файла-отчета");
        System.out.println("Укажите желаемую операцию: ");
        int operation = scanner.nextInt();

        switch (operation) {
            case 1:
                parser.parse();
            case 2:
               WriteReport.print();
        }
    }
}
