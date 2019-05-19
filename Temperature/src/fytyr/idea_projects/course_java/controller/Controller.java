package fytyr.idea_projects.course_java.controller;

import fytyr.idea_projects.course_java.model.Model;
import fytyr.idea_projects.course_java.view.View;

public class Controller {
    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    private void initView() {
        view.getTextFieldOut().setText(Double.toString(Math.round(model.convertTemperature())));
    }

    public void initModel() {
        try {
            model.setTemperatureIn(Double.parseDouble(view.getTextFieldIn().getText()));
        } catch (NumberFormatException e) {
            view.showWarningMessage();
        }
        model.setScaleIn(view.getComboIn().getSelectedIndex());
        model.setScaleOut(view.getComboOut().getSelectedIndex());
        initView();
    }
}
