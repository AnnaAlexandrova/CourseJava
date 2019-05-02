package InputOutput;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class CreateFile {
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("File.txt"))) {
            for (int i = 1; i <= 100; i++) {
                writer.println("Строка " + i);
            }
            writer.printf("%d Строка", 101);
            writer.println("Строка 102");
            writer.print("Строка 103");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
