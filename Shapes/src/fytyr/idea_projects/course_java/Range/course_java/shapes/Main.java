package fytyr.idea_projects.course_java.Range.course_java.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static Shapes[] getAreaSort(Shapes... shapes) {
        Comparator<Shapes> AreaComparator = (o1, o2) -> (int) (o1.getArea() - o2.getArea());
        Arrays.sort(shapes, AreaComparator);

        return shapes;
    }

    public static Shapes[] getPerimeterSort(Shapes... shapes) {
        Comparator<Shapes> PerimeterComparator = (o1, o2) -> (int) (o1.getPerimeter() - o2.getPerimeter());
        Arrays.sort(shapes, PerimeterComparator);

        return shapes;
    }


    public static void main(String[] args) {
        Square square1 = new Square(12);
        Square square2 = new Square(7.5);

        Triangle triangle1 = new Triangle(3, 2, 6, 6, 10, 2);
        Triangle triangle2 = new Triangle(-3, -2, -2, 6, 7, -5);

        Rectangle rectangle1 = new Rectangle(4, 10);
        Rectangle rectangle2 = new Rectangle(5, 8);

        Circle circle1 = new Circle(2.4);
        Circle circle2 = new Circle(7);

        Shapes[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};
        for (Shapes e : shapes) {
            System.out.println(e);
        }

        System.out.println("Фигура с максимальной площадью : ");
        System.out.println(getAreaSort(square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2)[shapes.length - 1]);

        System.out.println("Фигура со вторым по величине периметром : ");
        System.out.println(getPerimeterSort(square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2)[shapes.length - 2]);
    }
}
