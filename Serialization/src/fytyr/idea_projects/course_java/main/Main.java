package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.matrix.Matrix;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.bin"))) {
            out.writeObject(new Matrix(10, 1));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.bin"))) {
            Matrix matrix = (Matrix) in.readObject();
            matrix.print();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
