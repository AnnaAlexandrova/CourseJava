package fytyr.idea_projects.course_java.shapes.Shape;

public class Triangle implements Shape {
    private final static double EPSILON = 1.0e-10;

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (Math.abs((x3 - x1) * (y2 - y1) - (x2 - x1) * (y3 - y1)) <= EPSILON) {
            throw new IllegalArgumentException("Точки с заданными координатами лежат на одной прямой");
        }

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    private void makeExceptionCheck() {
        if (Math.abs((x3 - x1) * (y2 - y1) - (x2 - x1) * (y3 - y1)) <= EPSILON) {
            throw new IllegalArgumentException("Точки с заданными координатами лежат на одной прямой");
        }
    }

    public void setX1(double x1) {
        this.x1 = x1;
        makeExceptionCheck();
    }

    public void setY1(double y1) {
        this.y1 = y1;
        makeExceptionCheck();
    }

    public void setX2(double x2) {
        this.x2 = x2;
        makeExceptionCheck();
    }

    public void setY2(double y2) {
        this.y2 = y2;
        makeExceptionCheck();
    }

    public void setX3(double x3) {
        this.x3 = x3;
        makeExceptionCheck();
    }

    public void setY3(double y3) {
        this.y3 = y3;
        makeExceptionCheck();
    }

    private static double getSideLength(double fromX, double fromY, double toX, double toY) {
        return Math.sqrt(Math.pow((toX - fromX), 2) + (Math.pow((toY - fromY), 2)));
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - getSideLength(x1, y1, x2, y2)) *
                (semiPerimeter - getSideLength(x1, y1, x3, y3)) * (semiPerimeter - getSideLength(x2, y2, x3, y3)));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) + getSideLength(x1, y1, x3, y3) + getSideLength(x2, y2, x3, y3);
    }

    @Override
    public String toString() {
        return "[Type : Triangle, Points : (" + this.x1 + ", " + this.y1 + "; " + this.x2 + ", " + this.y2 + "; " + this.x3 + ", " + this.y3 +
                ") Width = " + this.getWidth() + ", Height = " + this.getHeight() +
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
        Triangle triangle = (Triangle) object;

        return x1 == triangle.x1 && y1 == triangle.y1 &&
                x2 == triangle.x2 && y2 == triangle.y2 &&
                x3 == triangle.x3 && y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        int prime = 17;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
