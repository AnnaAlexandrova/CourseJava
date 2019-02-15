package fytyr.idea_projects.course_java.Range.course_java.Main;

import fytyr.idea_projects.course_java.Range.course_java.shape.*;

import static fytyr.idea_projects.course_java.Range.course_java.Main.AreaComparator.makeAreaSort;
import static fytyr.idea_projects.course_java.Range.course_java.Main.PerimeterComparator.makePerimeterSort;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(12);
        Square square2 = new Square(7.5);

        Triangle triangle1 = new Triangle(3, 2, 6, 6, 10, 2);
        Triangle triangle2 = new Triangle(-3, -2, -2, 6, 7, -5);

        Rectangle rectangle1 = new Rectangle(4, 10);
        Rectangle rectangle2 = new Rectangle(5, 8);

        Circle circle1 = new Circle(2.4);
        Circle circle2 = new Circle(7);

        Shape[] shapes = {square1, square2, triangle1, triangle2, rectangle1, rectangle2, circle1, circle2};
        for (Shape e : shapes) {
            System.out.println(e);
        }

        System.out.println("Фигура с максимальной площадью : ");
        makeAreaSort(shapes);
        System.out.println(shapes[shapes.length - 1]);

        System.out.println("Фигура со вторым по величине периметром : ");
        makePerimeterSort(shapes);
        System.out.println(shapes[shapes.length - 2]);
    }
}
