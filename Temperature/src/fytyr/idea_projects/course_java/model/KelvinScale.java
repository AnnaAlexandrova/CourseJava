package fytyr.idea_projects.course_java.model;

public class KelvinScale implements Scale {
    @Override
    public Double convertToCelsius(Double degree) {
        return degree - 273.15;
    }

    @Override
    public Double convertFromCelsius(Double degree) {
        return degree + 273.15;
    }
}
