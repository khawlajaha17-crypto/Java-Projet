package app;

import app.controllers.StudentController;

public class Main {
    public static void main(String[] args) {
        StudentController controller = new StudentController();
        controller.menu();
    }
}
