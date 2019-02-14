package fytyr.idea_projects.course_java.Range.course_java.shapes;

public interface Shapes {
    double EPSILON = 1.0e-10;

    double getWidth();

    double getHeight();

    double getArea();

    double getPerimeter();

    int prime = 17;

    String toString();

    int hashCode();

    boolean equals(Object object);
}
