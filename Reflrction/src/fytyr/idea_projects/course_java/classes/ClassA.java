package fytyr.idea_projects.course_java.classes;

public class ClassA {
    private int x;
    private int y;

    public ClassA(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getMax() {
        return Math.max(x, y);
    }

    public int getMin() {
        return Math.min(x, y);
    }
}
