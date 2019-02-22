package fytyr.idea_projects.course_java.shapes.Main;

import fytyr.idea_projects.course_java.shapes.Shape.Shape;

public class PerimeterComparator implements java.util.Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.getPerimeter(), o2.getPerimeter());
    }
}
