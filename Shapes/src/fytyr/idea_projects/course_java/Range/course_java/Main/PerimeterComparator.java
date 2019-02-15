package fytyr.idea_projects.course_java.Range.course_java.Main;

import fytyr.idea_projects.course_java.Range.course_java.shape.Shape;

import java.util.Arrays;
import java.util.Comparator;

public class PerimeterComparator {
    public static void makePerimeterSort(Shape... shapes) {
        Comparator<Shape> PerimeterComparator = (Comparator.comparingDouble(Shape::getPerimeter));
        Arrays.sort(shapes, PerimeterComparator);
    }
}
