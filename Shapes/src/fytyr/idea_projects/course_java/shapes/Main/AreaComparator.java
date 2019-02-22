package fytyr.idea_projects.course_java.shapes.Main;

import fytyr.idea_projects.course_java.shapes.Shape.Shape;

public class AreaComparator implements java.util.Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}

