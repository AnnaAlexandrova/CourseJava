package fytyr.idea_projects.course_java.model;

public class Kelvin implements Scale {
    @Override
    public Double convertTo(Double degree) {
        return degree - 273.15;
    }

    @Override
    public Double convertFrom(Double degree) {
        return degree + 273.15;
    }
}
