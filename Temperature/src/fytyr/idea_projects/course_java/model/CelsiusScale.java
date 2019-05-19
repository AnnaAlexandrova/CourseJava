package fytyr.idea_projects.course_java.model;

public class CelsiusScale implements Scale {
    @Override
    public Double convertToCelsius(Double degree) {
        return degree;
    }

    @Override
    public Double convertFromCelsius(Double degree) {
        return degree;
    }
}
