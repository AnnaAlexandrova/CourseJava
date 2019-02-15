package fytyr.idea_projects.course_java.Range.course_java.Main;

import fytyr.idea_projects.course_java.Range.course_java.shape.Shape;

import java.util.Arrays;
import java.util.Comparator;

public class AreaComparator {
    public static void makeAreaSort(Shape... shapes) {
        Comparator<Shape> AreaComparator = (Comparator.comparingDouble(Shape::getArea));
        Arrays.sort(shapes, AreaComparator);
    }
}
