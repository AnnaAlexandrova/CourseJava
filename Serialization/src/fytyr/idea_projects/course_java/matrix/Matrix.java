package fytyr.idea_projects.course_java.matrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Matrix implements Serializable {
    private static final long serialVersionUID = 1L;
    private int[][] array;

    public Matrix(int size, int value) {
        this.array = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    array[i][j] = 0;
                } else {
                    array[i][j] = value;
                }
            }
        }
    }

    public void print() {
        for (int[] arr : array) {
            for (int e : arr) {
                System.out.printf("%3d", e);
            }
            System.out.println();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        getHalfMatrix();
        out.writeObject(array);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        array = (int[][]) in.readObject();

        getFullMatrix();

    }

    private void getHalfMatrix() {
        int[][] result = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = new int[i + 1];
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = i < j ? array[j][i] : array[i][j];
            }
        }
        array = result;
    }

    private void getFullMatrix() {
        int length = array.length;
        int[][] result = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result[i][j] = i < j ? array[j][i] : array[i][j];
            }
        }
        array = result;
    }
}
