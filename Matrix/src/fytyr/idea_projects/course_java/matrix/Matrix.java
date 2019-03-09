package fytyr.idea_projects.course_java.matrix;

import fytyr.idea_projects.course_java.main.MatrixException;
import fytyr.idea_projects.course_java.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        MatrixException.illegalObjectSizeException(rowsCount);
        MatrixException.illegalObjectSizeException(columnsCount);

        this.rows = new Vector[rowsCount];
        for (int i = 0; i < rowsCount; ++i) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];
        for (int i = 0; i < matrix.rows.length; ++i) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        MatrixException.notNullArgumentException(array);
        MatrixException.illegalObjectSizeException(array.length);

        this.rows = new Vector[array.length];

        int length = getMaxLength(array);
        for (int i = 0; i < array.length; i++) {
            MatrixException.illegalObjectSizeException(array[i].length);
            rows[i] = new Vector(length, array[i]);
        }
    }

    public Matrix(Vector[] newRows) {
        MatrixException.notNullArgumentException(newRows);
        MatrixException.illegalObjectSizeException(newRows.length);

        int length = getMaxLength(newRows);
        this.rows = new Vector[newRows.length];
        for (int i = 0; i < newRows.length; i++) {
            rows[i] = new Vector(length);
            rows[i].addVector(newRows[i]);
        }
    }

    private static int getMaxLength(double[][] array) {
        int maxLength = 0;
        for (int i = 1; i < array.length; i++) {
            maxLength = Math.max(array[i].length, array[i - 1].length);
        }
        MatrixException.illegalObjectSizeException(maxLength);
        return maxLength;
    }

    private static int getMaxLength(Vector[] rows) {
        int maxLength = 0;
        for (int i = 1; i < rows.length; i++) {
            maxLength = Math.max(rows[i].getSize(), rows[i - 1].getSize());
        }
        MatrixException.illegalObjectSizeException(maxLength);
        return maxLength;
    }

    private static void makeOneSize(Vector[] vectors) {
        for (int i = 0; i <= vectors.length; i++) {
            if (vectors[i].getSize() != vectors[i + 1].getSize()) {
                for (int j = i + 1; j < vectors.length; j++) {
                    vectors[i].makeOneSize(vectors[j]);
                }
                vectors[vectors.length - 1].makeOneSize(vectors[0]);
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int rowIndex) {
        MatrixException.indexOutOfMatrixBoundsException(rowIndex, rows.length);
        return new Vector(rows[rowIndex]);
    }

    public void setRow(int rowIndex, Vector newRow) {
        MatrixException.indexOutOfMatrixBoundsException(rowIndex, rows.length);
        MatrixException.notNullArgumentException(newRow);

        int rowLength = newRow.getSize();

        double[] tmp = new double[rowLength];
        for (int i = 0; i < rowLength; i++) {
            tmp[i] = newRow.getComponent(i);
        }
        this.rows[rowIndex] = new Vector(rowLength, tmp);
    }

    public Vector getColumn(int columnIndex) {
        MatrixException.indexOutOfMatrixBoundsException(columnIndex, getColumnsCount());

        Vector column = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(columnIndex));
        }
        return column;
    }

    public void transpose() {
        Vector[] tmp = new Vector[getColumnsCount()];
        for (int i = 0; i < getColumnsCount(); i++) {
            tmp[i] = getColumn(i);
        }
        rows = tmp;
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyOnScalar(scalar);
        }
    }

    public double getDeterminant() {
        MatrixException.notQuadraticMatrixException(getRowsCount(), getColumnsCount());

        if (rows.length == 1) {
            return getFirstOrderDeterminant();
        } else if (rows.length == 2) {
            return getSecondOrderDeterminant();
        }

        return getArbitraryOrderDeterminant();
    }

    private double getFirstOrderDeterminant() {
        return rows[0].getComponent(0);
    }

    private double getSecondOrderDeterminant() {
        return rows[0].getComponent(0) * rows[1].getComponent(1) -
                rows[0].getComponent(1) * rows[1].getComponent(0);
    }

    private double getArbitraryOrderDeterminant() {
        double det = 0;
        int rowNumber = 0; //раскладывать определитель будем по первой строке

        for (int i = 0; i < rows[rowNumber].getSize(); ++i) {
            det += (rows[i].getComponent(rowNumber) * Math.pow(-1, (i + rowNumber)) * getMinor(i, rowNumber));
        }
        return det;
    }

    private double getMinor(int column, int row) {
        Matrix minor = new Matrix(rows.length - 1, rows.length - 1);

        int newColumn = 0;

        for (int i = 0; i < rows.length; ++i) {
            if (i == column) {
                continue;
            }

            int newRow = 0;

            for (int j = 0; j < rows.length; ++j) {
                if (j == row) {
                    continue;
                }
                minor.rows[newColumn].setComponent(newRow, rows[i].getComponent(j));
                newRow++;
            }
            newColumn++;
        }

        if (minor.rows.length == 2) {
            return minor.getSecondOrderDeterminant();
        }

        return minor.getArbitraryOrderDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{");
        for (Vector vector : rows) {
            string.append(vector.toString()).append(", ");
        }
        int endIndex = string.lastIndexOf(", ");
        string.deleteCharAt(endIndex);
        string.append("}");
        return string.toString();
    }

    public Vector multiplyOnVector(Vector vector) {
        MatrixException.notAllowedVectorSizeException(getColumnsCount(), vector.getSize());

        int length = getRowsCount();
        Vector result = new Vector(length);
        for (int i = 0; i < length; i++) {
            result.setComponent(i, Vector.multiplicationScalar(rows[i], vector));
        }
        return result;
    }

    public void addMatrix(Matrix matrix) {
        MatrixException.notAllowedMatrixSizeException(getRowsCount(), matrix.getRowsCount(), getColumnsCount(), matrix.getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].addVector(matrix.getRow(i));
        }
    }

    public void subMatrix(Matrix matrix) {
        MatrixException.notAllowedMatrixSizeException(getRowsCount(), matrix.getRowsCount(), getColumnsCount(), matrix.getColumnsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subVector(matrix.getRow(i));
        }
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        MatrixException.notAllowedMatrixSizeException(matrix1.getColumnsCount(), matrix2.getRowsCount());

        int rowsCount = matrix1.getRowsCount();
        int columnsCount = matrix2.getColumnsCount();

        Matrix result = new Matrix(rowsCount, columnsCount);
        Vector row = new Vector(columnsCount);

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                row.setComponent(j, Vector.multiplicationScalar(matrix1.rows[i], matrix2.getColumn(j)));
            }
            result.setRow(i, row);
        }
        return result;
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        MatrixException.notAllowedMatrixSizeException(matrix1.getRowsCount(), matrix2.getRowsCount(), matrix1.getColumnsCount(), matrix2.getColumnsCount());

        Matrix result = new Matrix(matrix1);
        result.addMatrix(matrix2);
        return result;
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        MatrixException.notAllowedMatrixSizeException(matrix1.getRowsCount(), matrix2.getRowsCount(), matrix1.getColumnsCount(), matrix2.getColumnsCount());

        Matrix result = new Matrix(matrix1);
        result.subMatrix(matrix2);
        return result;
    }
}
