package fytyr.idea_projects.course_java.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть > 0");
        }
        this.components = new double[size];
    }

    public Vector(Vector vector) {
        if (vector.components == null) {
            throw new NullPointerException("Вектор равен NULL");
        }
        if (vector.components.length == 0) {
            throw new IllegalArgumentException("Длина переданного вектора равна 0");
        }
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("Вектор равен NULL");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина переданного массива равна 0");
        }
        this.components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть > 0");
        }
        if (array == null) {
            throw new NullPointerException("Вектор равен NULL");
        }
        this.components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{ ");
        for (double component : components) {
            string.append(component).append(", ");
        }
        int endIndex = string.lastIndexOf(", ");
        string.deleteCharAt(endIndex);
        string.append("}");
        return string.toString();
    }

    public void makeOneSize(Vector vector) {
        if (components.length < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }
    }

    public void addVector(Vector vector) {
        makeOneSize(vector);
        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] += vector.components[i];
        }
    }

    public void subVector(Vector vector) {
        makeOneSize(vector);
        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] -= vector.components[i];
        }
    }

    public void multiplyOnScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            this.components[i] *= scalar;
        }
    }

    public void turn() {
        multiplyOnScalar(-1);
    }

    public double getLength() {
        double length = 0;
        for (double v : components) {
            length += Math.pow(v, 2);
        }
        return Math.sqrt(length);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        this.components[index] = component;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Vector vector = (Vector) object;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.addVector(vector2);
        return result;
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subVector(vector2);
        return result;
    }

    public static double multiplicationScalar(Vector vector1, Vector vector2) {
        double result = 0;
        int length = Math.min(vector1.getSize(), vector2.getSize());
        for (int i = 0; i < length; i++) {
            result += vector1.components[i] * vector2.components[i];
        }
        return result;
    }
}
