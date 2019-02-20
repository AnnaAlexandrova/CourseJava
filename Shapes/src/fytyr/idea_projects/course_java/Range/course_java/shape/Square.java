package fytyr.idea_projects.course_java.Range.course_java.shape;

public class Square implements Shape {
    private final double EPSILON = 1.0e-10;

    private double sideLength;

    public Square(double sideLength) {
        if (sideLength <= EPSILON) {
            throw new IllegalArgumentException("sideLength must be > 0");
        }
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        if (sideLength <= EPSILON) {
            throw new IllegalArgumentException("sideLength must be > 0");
        }
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return "[Type : Square, sideLength = " + this.sideLength + ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Square square = (Square) object;

        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(sideLength);
    }
}
