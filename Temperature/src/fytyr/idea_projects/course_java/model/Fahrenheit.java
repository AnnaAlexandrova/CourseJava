package fytyr.idea_projects.course_java.model;

public class Fahrenheit implements Scale {
    @Override
    public Double convertTo(Double degree) {
        return (degree - 32) / 1.8;
    }

    @Override
    public Double convertFrom(Double degree) {
        return degree * 1.8 + 32;
    }
}
