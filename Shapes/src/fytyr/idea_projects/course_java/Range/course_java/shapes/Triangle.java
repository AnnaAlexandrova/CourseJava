package fytyr.idea_projects.course_java.Range.course_java.shapes;

public class Triangle implements Shapes {
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

    public void setPoints(double x1, double y1, double x2, double y2, double x3, double y3) {
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

    public double getSide1Length() {
        return Math.sqrt(Math.pow((x2 - x1), 2) + (Math.pow((y2 - y1), 2)));
    }

    public double getSide2Length() {
        return Math.sqrt(Math.pow((x3 - x1), 2) + (Math.pow((y3 - y1), 2)));
    }

    public double getSide3Length() {
        return Math.sqrt(Math.pow((x3 - x2), 2) + (Math.pow((y3 - y2), 2)));
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
        return Math.sqrt(semiPerimeter * (semiPerimeter - getSide1Length()) *
                (semiPerimeter - getSide2Length()) * (semiPerimeter - getSide3Length()));
    }

    @Override
    public double getPerimeter() {
        return getSide1Length() + getSide2Length() + getSide3Length();
    }

    public String toString() {
        return "[Points : (" + this.x1 + ", " + this.y1 + "; " + this.x2 + ", " + this.y2 + "; " + this.x3 + ", " + this.y3 +
                ") Width = " + this.getWidth() + ", Height = " + this.getHeight() +
                ", Area = " + this.getArea() + ", Perimeter = " + this.getPerimeter() + "]";
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) object;

        return x1 == triangle.x1 && y1 == triangle.y1 &&
                x2 == triangle.x2 && y2 == triangle.y2 &&
                x3 == triangle.x3 && y3 == triangle.y3;
    }

    public int hashCode() {
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
