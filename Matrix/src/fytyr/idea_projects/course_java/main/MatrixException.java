package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.vector.Vector;

public class MatrixException extends RuntimeException {
    public MatrixException(String message) {
        super(message);
    }

    public static void NotPositiveArgumentException(int argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("Переданный аргумент не может быть меньше 0");
        }
    }

    public static void NotGreaterThenZeroArgumentException(int argument) {
        if (argument <= 0) {
            throw new IllegalArgumentException("Переданный аргумент должен быть больше 0");
        }
    }

    public static void NotNullArgumentException(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Аргумент равен null");
        }
        for (int i = 0; i < vectors.length; i++) {
            if (vectors[i] == null) {
                throw new NullPointerException("Элемент массива по индексу " + i + " равен null");
            }
        }
    }

    public static void NotNullArgumentException(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Аргумент равен null");
        }
    }

    public static void NotNullArgumentException(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Аргумент равен null");
        }
    }

    public static void NotAllowedMatrixSizeException(int size1, int size2, int messageIndex) {
        if (size1 != size2) {
            throw new IllegalArgumentException(getMessage(messageIndex).toString());
        }
    }

    private static StringBuilder getMessage(int index) {
        StringBuilder message = new StringBuilder();
        message.append("Не подходящий размер матрицы : ");
        switch (index) {
            case 1:
                message.append("матрица должна быть квадратной");
                break;
            case 2:
                message.append("количество строк в матрице должно быть равно длине вектора");
                break;
            case 3:
                message.append("матрицы должны быть одного размера");
                break;
            case 4:
                message.append("количечство строк одной матрицы должно быть равно количеству колонок в другой");
                break;
        }
        return message;
    }
}

