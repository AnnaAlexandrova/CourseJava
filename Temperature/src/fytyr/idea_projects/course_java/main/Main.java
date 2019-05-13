package fytyr.idea_projects.course_java.main;

import fytyr.idea_projects.course_java.controller.Controller;
import fytyr.idea_projects.course_java.model.Model;
import fytyr.idea_projects.course_java.view.View;

public class Main {
    public static void main(String[] args) {
        View myWindow = new View();
        Model model = new Model();
        Controller controller = new Controller(model, myWindow);
        controller.initController();
    }
}
