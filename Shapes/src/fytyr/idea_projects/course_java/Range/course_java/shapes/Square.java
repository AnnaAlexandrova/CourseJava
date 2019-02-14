package fytyr.idea_projects.course_java.Range.course_java.shapes;

public class Square implements Shapes {
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

    public String toString() {
        return "[sideLength = " + this.sideLength + ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Square square = (Square) object;

        return sideLength == square.sideLength;
    }

    public int hashCode() {
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        return hash;
    }
}
