import Service.CreateReport;
import Service.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
       /* Scanner scanner = new Scanner(System.in);
        System.out.println("1 - вызов операции парсинга файлов перевода из input" +
                         "\n2 - вызов операции вывода списка всех переводов из файла-отчета");
        System.out.println("Укажите желаемую операцию: ");
        int operation = scanner.nextInt();*/

       /* switch (operation){
            case 1:
                Parser parser = new Parser();
                parser.parse();
            case 2:
                CreateReport.createReport();
        }*/

        Parser parser = new Parser();
        parser.parse();
    }
}
