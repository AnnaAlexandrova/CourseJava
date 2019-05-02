package InputOutput;

import java.io.*;

public class CopyFile {
    public static void main(String[] args) {
        try (BufferedInputStream streamIn = new BufferedInputStream
                (new FileInputStream("C:/Users/fytyr/OneDrive/Рабочий стол/AcademIT/ООП/Zadachi_na_kurs.docx"));
             BufferedOutputStream streamOut = new BufferedOutputStream(new FileOutputStream("file.docx"))) {
            int c;
            byte[] array = new byte[100000];
            while ((c = streamIn.read(array)) != -1)
                streamOut.write(array, 0, c);
            streamOut.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
