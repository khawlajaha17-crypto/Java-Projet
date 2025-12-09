package app.controllers;

import app.models.Student;
import app.utils.CsvUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentController {

    private List<Student> students = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int choice;

        do {
            System.out.println("===== MENU =====");
            System.out.println("1 - Import CSV");
            System.out.println("2 - Show Students");
            System.out.println("3 - Add Student");
            System.out.println("4 - Export CSV");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> importCsv();
                case 2 -> showStudents();
                case 3 -> addStudent();
                case 4 -> exportCsv();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    private void importCsv() {
        students = CsvUtils.importStudents("students.csv");
        System.out.println("Imported " + students.size() + " students.");
    }

    private void showStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("===== STUDENTS =====");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void addStudent() {
        scanner.nextLine(); // Fix Scanner Bug

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Module: ");
        String module = scanner.nextLine();

        System.out.print("Note: ");
        double note = scanner.nextDouble();

        students.add(new Student(name, module, note));

        System.out.println("Student added!");
    }

    private void exportCsv() {
        CsvUtils.exportStudents("students.csv", students);
        System.out.println("CSV exported.");
    }
}
