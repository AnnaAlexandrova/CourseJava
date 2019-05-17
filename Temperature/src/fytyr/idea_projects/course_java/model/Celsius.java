package fytyr.idea_projects.course_java.model;

public class Celsius implements Scale {
    @Override
    public Double convertTo(Double degree) {
        return degree;
    }

    @Override
    public Double convertFrom(Double degree) {
        return degree;
    }
}
