package fytyr.idea_projects.course_java.Range.course_java.shape;

public class Rectangle implements Shape {
    private final double EPSILON = 1.0e-10;

    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= EPSILON || height <= EPSILON) {
            throw new IllegalArgumentException("Width and height must be > 0");
        }
        this.width = width;
        this.height = height;
    }

    public void setWidth(double width) {
        if (width <= EPSILON) {
            throw new IllegalArgumentException("Width must be > 0");
        }
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= EPSILON) {
            throw new IllegalArgumentException("Height must be > 0");
        }
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "[Width = " + this.width + ", Height = " + this.height +
                ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) object;

        return width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}
