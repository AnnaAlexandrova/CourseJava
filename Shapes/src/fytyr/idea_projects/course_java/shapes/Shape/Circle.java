package fytyr.idea_projects.course_java.shapes.Shape;

public class Circle implements Shape {
    private final static double EPSILON = 1.0e-10;

    private double radius;

    public Circle(double radius) {
        if (radius <= EPSILON) {
            throw new IllegalArgumentException("Radius must be > 0");
        }
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= EPSILON) {
            throw new IllegalArgumentException("Radius must be > 0");
        }
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "[Type : Circle, radius = " + this.radius + ", Width = " + this.getWidth() + ", Height = " + this.getHeight()
                + ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Circle circle = (Circle) object;

        return radius == circle.radius;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}
