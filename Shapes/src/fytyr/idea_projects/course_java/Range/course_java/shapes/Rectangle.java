package fytyr.idea_projects.course_java.Range.course_java.shapes;

public class Rectangle implements Shapes {
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public String toString() {
        return "[Width = " + this.width + ", Height = " + this.height +
                ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) object;

        return width == rectangle.width && height == rectangle.height;
    }

    public int hashCode() {
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }
}
