package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.matrix.Matrix;
import fytyr.idea_projects.course_java.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 2);
        System.out.println("Matrix1 size: " + matrix1.getRowsCount() + " " + matrix1.getColumnsCount());

        double[][] array1 = {{2, 4, 5, 6}, {3, 2, 5, 7}};
        Matrix matrix2 = new Matrix(array1);
        matrix2.transpose();
        System.out.println("Matrix2:" + matrix2);

        Matrix matrix3 = new Matrix(matrix2);
        Vector newRow = new Vector(new double[]{3, 6});
        matrix3.setRow(1, newRow);
        System.out.println("Matrix3 string_1: " + matrix3.getRow(1));
        System.out.println("Matrix3 column_1: " + matrix3.getColumn(1));

        System.out.println("Matrix3: " + matrix3);
        matrix3.multiplyOnScalar(2);
        System.out.println("Matrix3 * 2 = " + matrix3);

        Vector[] rows1 = new Vector[3];
        for (int i = 0; i < rows1.length; ++i) {
            rows1[i] = new Vector(2);
        }
        Matrix matrix4 = new Matrix(rows1);
        System.out.println("Matrix4: " + matrix4);

        double[][] array2 = {
                {3.00, 6.01, 2.00, 7.00, 3.00},
                {1.00, 0.00, 5.00, 7.00, 2.18},
                {2.56, 5.00, 8.00, 9.70, 0.00},
                {3.00, 1.25, 5.00, 2.00, 4.00},
                {7.00, 0.00, 1.00, 0.00, 3.00}};

        Matrix matrix5 = new Matrix(array2);
        System.out.println("Matrix5 determinant + " + matrix5.getDeterminant());

        double[][] array3 = {{6, 7, 8}, {8, 5, 0, 4}, {1, 7}};
        Matrix matrix6 = new Matrix(array3);
        System.out.println("Matrix6: " + matrix6);

        Vector[] rows2 = new Vector[3];
        for (int i = 0; i < rows2.length; i++) {
            rows2[i] = new Vector(array3[i]);
        }

        Matrix matrix7 = new Matrix(rows2);
        System.out.println("Matrix7: " + matrix7);

        Vector vector = new Vector(new double[]{2, 4, 7, 3});
        System.out.println("Matrix7 * rows2 = " + matrix7.multiplyOnVector(vector));

        matrix2.addMatrix(matrix3);
        System.out.println("Matrix2 + matrix3 = " + matrix2);

        matrix2.subMatrix(matrix3);
        System.out.println("Matrix2 - matrix3 = " + matrix2);

        matrix3.transpose();
        Matrix matrix8 = new Matrix(Matrix.multiplication(matrix2, matrix3));
        System.out.println("Matrix2 * matrix3 = " + matrix8);

        matrix2.transpose();
        Matrix matrix9 = new Matrix(Matrix.addition(matrix2, matrix3));
        System.out.println("Matrix2 + matrix3 = " + matrix9);

        Matrix matrix10 = new Matrix(Matrix.subtraction(matrix2, matrix3));
        System.out.println("Matrix2 - matrix3 = " + matrix10);
    }
}
