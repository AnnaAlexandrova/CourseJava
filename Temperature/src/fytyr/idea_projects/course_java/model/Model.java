package fytyr.idea_projects.course_java.model;

public class Model {
    private double temperatureIn;
    private int scaleIn;
    private int scaleOut;

    public Model() {
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
        if (scaleIn == 0) {
            if (scaleOut == 1) {
                return (temperatureIn - 32) / 1.8;
            }
            return (temperatureIn + 459.67) / 1.8;
        } else if (scaleIn == 1) {
            if (scaleOut == 0) {
                return temperatureIn * 1.8 + 32;
            }
            return temperatureIn + 273.15;
        }
        if (scaleOut == 0) {
            return temperatureIn * 1.8 - 459.67;
        }
        return temperatureIn - 273.15;
    }
}
