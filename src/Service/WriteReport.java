package Service;

import java.io.FileInputStream;
import java.io.IOException;

public class WriteReport {
    private static final String REPORT = "report.txt";

    public static void print() {
        try (FileInputStream fin = new FileInputStream(REPORT)) {
            int i;
            String line = "";
            while ((i = fin.read()) != -1) {
                line = line + (char) i;
            }
            System.out.println(line);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла-отчета " + e.getMessage());
        }
    }
}
