package fytyr.idea_projects.course_java.Range.course_java.shapes;

public class Circle implements Shapes {
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

    public double getWidth() {
        return 2 * radius;
    }

    public double getHeight() {
        return 2 * radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public String toString() {
        return "[radius = " + this.radius + ", Width = " + this.getWidth() + ", Height = " + this.getHeight()
                + ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Circle circle = (Circle) object;

        return radius == circle.radius;
    }

    public int hashCode() {
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }
}
