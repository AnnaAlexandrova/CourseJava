package fytyr.idea_projects.course_java.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;
    private final static double EPSILON = 1.0e-10;

    public Vector(int size) {
        if (size <= EPSILON) {
            throw new IllegalArgumentException("Размер вектора должен быть > 0");
        }
        this.vector = new double[size];
    }

    public Vector(Vector vector) {
        this.vector = vector.vector;
    }

    public Vector(double[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Вектор равен NULL или содержит массив длины 0");
        }
        this.vector = array;
    }

    public Vector(int size, double[] array) {
        if (size <= EPSILON) {
            throw new IllegalArgumentException("Размер вектора должен быть > 0");
        }
        if (size < array.length) {
            size = array.length;
        }
        this.vector = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return vector.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public void addVector(Vector vector2) {
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i] + vector2.vector[i];
        }
    }

    public void subVector(Vector vector2) {
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i] - vector2.vector[i];
        }
    }

    public void multiScalar(double scalar) {
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i] * scalar;
        }
    }

    public void turn() {
        for (int i = 0; i < vector.length; i++) {
            this.vector[i] = vector[i] * (-1);
        }
    }

    public double getLength() {
        double length = 0;
        for (double v : vector) {
            length += Math.pow(v, 2);
        }
        return Math.abs(Math.sqrt(length));
    }

    public double getComponent(int index) {
        return vector[index];
    }

    public void setComponent(int index, double component) {
        this.vector[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) object;

        for (int i = 0; i < vector.length; ++i) {
            if (Double.doubleToLongBits(vector[i]) != Double.doubleToLongBits(v.vector[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector);
    }

    public static Vector addition(Vector vector, Vector vector2) {
        Vector result = new Vector(Math.min(vector.getSize(), vector2.getSize()));
        for (int i = 0; i < result.getSize(); i++) {
            result.vector[i] = vector.vector[i] + vector2.vector[i];
        }
        return result;
    }

    public static Vector subtraction(Vector vector, Vector vector2) {
        Vector result = new Vector(Math.min(vector.getSize(), vector2.getSize()));
        for (int i = 0; i < result.getSize(); i++) {
            result.vector[i] = vector.vector[i] - vector2.vector[i];
        }
        return result;
    }

    public static Vector multiplication(Vector vector, Vector vector2) {
        Vector result = new Vector(Math.min(vector.getSize(), vector2.getSize()));
        for (int i = 0; i < result.getSize(); i++) {
            result.vector[i] = vector.vector[i] * vector2.vector[i];
        }
        return result;
    }
}
