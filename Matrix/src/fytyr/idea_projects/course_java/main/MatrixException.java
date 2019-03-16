package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.vector.Vector;

public class MatrixException extends RuntimeException {
    public MatrixException(String message) {
        super(message);
    }

    public static void isIllegalObjectSizeException(int argument) {
        if (argument <= 0) {
            throw new IllegalArgumentException("Длина массива равна 0");
        }
    }

    public static void isIllegalMatrixSizeException(int argument) {
        if (argument <= 0) {
            throw new IllegalArgumentException("Количество строк и столбцов в матрице должно быть больше 0");
        }
    }

    public static void isIndexOutOfMatrixBoundsException(int index, int length) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("Переданный индекс выходит за рамки диапозона");
        }
    }

    public static void isNotNullArgumentException(Vector[] rows) {
        if (rows == null) {
            throw new NullPointerException("Переданный массив равен null");
        }
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == null) {
                throw new NullPointerException("Элемент массива по индексу " + i + " равен null");
            }
        }
    }

    public static void isNotNullArgumentException(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Переданный вектор равен null");
        }
    }

    public static void isNotNullArgumentException(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Переданный массив равен null");
        }
    }

    public static void isNotAllowedVectorSizeException(int size1, int size2) {
        if (size1 != size2) {
            throw new IllegalArgumentException("Количество строк в матрице должно быть равно длине вектора");
        }
    }

    public static void isNotAllowedMatrixSizeException(int linesCount1, int linesCount2, int columnsCount1, int columnsCount2) {
        if (linesCount1 != linesCount2 || columnsCount1 != columnsCount2) {
            throw new IllegalArgumentException("Матрицы должны быть одного размера");
        }
    }

    public static void isNotAllowedMatrixSizeException(int size1, int size2) {
        if (size1 != size2) {
            throw new IllegalArgumentException("Количечство строк одной матрицы должно быть равно количеству колонок в другой");
        }
    }

    public static void isNotQuadraticMatrixException(int size1, int size2) {
        if (size1 != size2) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }
    }
}

