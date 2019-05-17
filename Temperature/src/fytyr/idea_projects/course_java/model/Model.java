package fytyr.idea_projects.course_java.model;

public class Model {
    private double temperatureIn;
    private int scaleIn;
    private int scaleOut;
    private Scale[] scales;

    public Model(Scale[] scales) {
        this.scales = scales;
    }

    public void setTemperatureIn(double temperatureIn) {
        this.temperatureIn = temperatureIn;
    }

    public void setScaleIn(int scaleIn) {
        this.scaleIn = scaleIn;
    }

    public void setScaleOut(int scaleOut) {
        this.scaleOut = scaleOut;
    }

    public double transferTemperature() {
        if (scaleIn == scaleOut) {
            return temperatureIn;
        }
        double degreeToCelsius = scales[scaleIn].convertTo(temperatureIn);
        return scales[scaleOut].convertFrom(degreeToCelsius);
    }
}
