package fytyr.idea.projects.course_java.MyArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            ArrayList<String> strings = new ArrayList<>();

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }

            for (String s : strings) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
