package fytyr.idea_projects.course_java.matrix;

import fytyr.idea_projects.course_java.vector.Vector;

import java.util.Arrays;

import static fytyr.idea_projects.course_java.main.MatrixException.*;

import static fytyr.idea_projects.course_java.vector.Vector.*;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int linesCount, int columnsCount) {
        NotGreaterThenZeroArgumentException(linesCount);
        NotGreaterThenZeroArgumentException(columnsCount);

        this.vectors = new Vector[linesCount];
        for (int i = 0; i < linesCount; ++i) {
            vectors[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        NotNullArgumentException(matrix.vectors);
        NotGreaterThenZeroArgumentException(matrix.vectors.length);

        this.vectors = Arrays.copyOf(matrix.vectors, matrix.vectors.length);
        for (int i = 0; i < matrix.vectors.length; ++i) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        NotNullArgumentException(array);
        NotGreaterThenZeroArgumentException(array.length);

        this.vectors = new Vector[array.length];

        int length = getMaxLength(array);
        for (int i = 0; i < array.length; i++) {
            vectors[i] = new Vector(length, array[i]);
        }
    }

    private int getMaxLength(double[][] array) {
        int maxLength = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                maxLength = Math.max(array[i].length, array[j].length);
            }
        }
        return maxLength;
    }

    public Matrix(Vector[] newVectors) {
        NotNullArgumentException(newVectors);
        NotGreaterThenZeroArgumentException(newVectors.length);

        makeOneSize(newVectors);
        this.vectors = Arrays.copyOf(newVectors, newVectors.length);
    }

    private void makeOneSize(Vector[] vectors) {
        for (int i = 0; i < vectors.length - 1; i++) {
            if (vectors[i].getSize() != vectors[i + 1].getSize()) {
                for (int j = i + 1; j < vectors.length; j++) {
                    vectors[i].makeOneSize(vectors[j]);
                }
                vectors[vectors.length - 1].makeOneSize(vectors[0]);
            }
        }
    }

    public int getLinesCount() {
        return vectors.length;
    }

    public int getColumnsCount() {
        return vectors[0].getSize();
    }

    public Vector getString(int stringIndex) {
        NotPositiveArgumentException(stringIndex);
        return vectors[stringIndex];
    }

    public void setString(int stringIndex, Vector newString) {
        NotPositiveArgumentException(stringIndex);
        NotNullArgumentException(newString);
        NotGreaterThenZeroArgumentException(newString.getSize());

        this.vectors[stringIndex] = newString;
    }

    public Vector getColumn(int columnIndex) {
        NotPositiveArgumentException(columnIndex);
        Vector column = new Vector(vectors.length);
        for (int i = 0; i < vectors.length; i++) {
            column.setComponent(i, vectors[i].getComponent(columnIndex));
        }
        return column;
    }

    public void transpose() {
        Matrix tmp = new Matrix(getColumnsCount(), getLinesCount());
        for (int i = 0; i < getColumnsCount(); i++) {
            tmp.setString(i, getColumn(i));
        }
        vectors = tmp.vectors;
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector vector : vectors) {
            vector.multiplyOnScalar(scalar);
        }
    }

    public double getDeterminant() {
        NotAllowedMatrixSizeException(getLinesCount(), getColumnsCount(), 1);

        if (vectors.length == 1) {
            return getFirstOrderDeterminant();
        } else if (vectors.length == 2) {
            return getSecondOrderDeterminant();
        }

        return getArbitraryOrderDeterminant();
    }

    private double getFirstOrderDeterminant() {
        return vectors[0].getComponent(0);
    }

    private double getSecondOrderDeterminant() {
        return vectors[0].getComponent(0) * vectors[1].getComponent(1) -
                vectors[0].getComponent(1) * vectors[1].getComponent(0);
    }

    private double getArbitraryOrderDeterminant() {
        double det = 0;
        int lineNumber = 0; //раскладывать определитель будем по первой строке

        for (int i = 0; i < vectors[lineNumber].getSize(); ++i) {
            det += (vectors[i].getComponent(lineNumber) * Math.pow(-1, (i + lineNumber)) * getMinor(i, lineNumber));
        }
        return det;
    }

    private double getMinor(int column, int line) {
        Matrix minor = new Matrix(vectors.length - 1, vectors.length - 1);

        int newColumn = 0;

        for (int i = 0; i < vectors.length; ++i) {
            if (i == column) {
                continue;
            }

            int newLine = 0;

            for (int j = 0; j < vectors.length; ++j) {
                if (j == line) {
                    continue;
                }
                minor.vectors[newColumn].setComponent(newLine, vectors[i].getComponent(j));
                newLine++;
            }
            newColumn++;
        }

        if (minor.vectors.length == 2) {
            return minor.getSecondOrderDeterminant();
        }

        return minor.getArbitraryOrderDeterminant();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{");
        for (Vector vector : vectors) {
            string.append(vector.toString()).append(", ");
        }
        int endIndex = string.lastIndexOf(", ");
        string.deleteCharAt(endIndex);
        string.append("}");
        return string.toString();
    }

    public Vector multiplyOnVector(Vector vector) {
        NotAllowedMatrixSizeException(getLinesCount(), vector.getSize(), 2);

        int length = getColumnsCount();
        Vector result = new Vector(length);
        for (int i = 0; i < length; i++) {
            result.setComponent(i, multiplicationScalar(vector, getColumn(i)));
        }
        return result;
    }

    public void addMatrix(Matrix matrix) {
        NotAllowedMatrixSizeException(getLinesCount(), matrix.getLinesCount(), 3);
        NotAllowedMatrixSizeException(getColumnsCount(), matrix.getColumnsCount(), 3);

        for (int i = 0; i < getLinesCount(); i++) {
            vectors[i].addVector(matrix.getString(i));
        }
    }

    public void subMatrix(Matrix matrix) {
        NotAllowedMatrixSizeException(getLinesCount(), matrix.getLinesCount(), 3);
        NotAllowedMatrixSizeException(getColumnsCount(), matrix.getColumnsCount(), 3);

        for (int i = 0; i < getLinesCount(); i++) {
            vectors[i].subVector(matrix.getString(i));
        }
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        NotAllowedMatrixSizeException(matrix1.getColumnsCount(), matrix2.getLinesCount(), 4);

        int linesCount = matrix1.getLinesCount();
        int columnsCount = matrix2.getColumnsCount();
        Matrix result = new Matrix(linesCount, columnsCount);

        for (int i = 0; i < linesCount; i++) {
            Vector string = new Vector(columnsCount);
            result.setString(i, string);
            for (int j = 0; j < columnsCount; j++) {
                string.setComponent(j, multiplicationScalar(matrix1.getString(i), matrix2.getColumn(j)));
            }
        }
        return result;
    }

    public static Matrix addition(Matrix matrix1, Matrix matrix2) {
        NotAllowedMatrixSizeException(matrix1.getLinesCount(), matrix2.getLinesCount(), 3);
        NotAllowedMatrixSizeException(matrix1.getColumnsCount(), matrix2.getColumnsCount(), 3);

        Matrix result = new Matrix(matrix1);
        result.addMatrix(matrix2);
        return result;
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        NotAllowedMatrixSizeException(matrix1.getLinesCount(), matrix2.getLinesCount(), 3);
        NotAllowedMatrixSizeException(matrix1.getColumnsCount(), matrix2.getColumnsCount(), 3);

        Matrix result = new Matrix(matrix1);
        result.subMatrix(matrix2);
        return result;
    }
}
