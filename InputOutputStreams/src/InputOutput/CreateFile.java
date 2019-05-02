package InputOutput;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class CreateFile {
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("File.txt"))) {
            for (int i = 1; i <= 100; i++) {
                writer.println("������ " + i);
            }
            writer.printf("%d ������", 101);
            writer.println("������ 102");
            writer.print("������ 103");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
