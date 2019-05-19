package fytyr.idea_projects.course_java.model;

public class FahrenheitScale implements Scale {
    @Override
    public Double convertToCelsius(Double degree) {
        return (degree - 32) / 1.8;
    }

    @Override
    public Double convertFromCelsius(Double degree) {
        return degree * 1.8 + 32;
    }
}
