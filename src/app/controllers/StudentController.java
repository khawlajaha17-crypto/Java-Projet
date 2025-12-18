package app.controllers;

import app.models.Student;
import app.utils.CsvUtils;

import java.util.*;

public class StudentController {

    private List<Student> students;
    private Scanner scanner = new Scanner(System.in);

    public StudentController() {

        students = CsvUtils.importStudents("notes.csv");
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Ajouter Etudiant");
            System.out.println("2 - Aficher Etudiants");
            System.out.println("3 - Recherche d'Etudiant");
            System.out.println("4 - Export CSV");
            System.out.println("0 - Exit");
            System.out.print("Choix: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> showStudents();
                case 3 -> searchStudent();
                case 4 -> exportCsv();
            }
        } while (choice != 0);
    }

    private void addStudent() {
        scanner.nextLine();
        System.out.print("Nom d'Etudiant: ");
        String name = scanner.nextLine();

        Student s = new Student(name);

        System.out.print("Nombre des modules: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Nom_Module: ");
            String module = scanner.nextLine();

            double note;
            while (true) {
                System.out.print("Note: ");
                try {
                    note = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    if (note < 0 || note > 20) continue;
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid note.");
                }
            }
            s.addNote(module, note);
        }

        students.add(s);
        System.out.println("Student ajouté.");
    }

    private void showStudents() {
        if (students.isEmpty()) {
            System.out.println("Il n'y a pas encore d'étudiants.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void searchStudent() {
        scanner.nextLine();
        System.out.print("Nom_Etudiant: ");
        String name = scanner.nextLine();

        Student s = students.stream()
                .filter(st -> st.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (s == null) {
            System.out.println("Étudiant introuvable.");
            return;
        }

        System.out.println(s);
        System.out.println("1 - Modifier");
        System.out.println("2 - Supprimer");
        System.out.println("0 - Retour");
        int ch = scanner.nextInt();

        if (ch == 1) modifyStudent(s);
        if (ch == 2) {
            students.remove(s);
            System.out.println("Étudiant supprimé.");
        }
    }

    private void modifyStudent(Student s) {
        scanner.nextLine();
        System.out.print("Nom_module: ");
        String module = scanner.nextLine();

        double note;
        while (true) {
            System.out.print("Nouvelle note: ");
            try {
                note = Double.parseDouble(scanner.nextLine().replace(",", "."));
                if (note < 0 || note > 20) continue;
                break;
            } catch (Exception e) {
                System.out.println("Invalid note.");
            }
        }

        s.addNote(module, note);
        System.out.println("Étudiant modifié.");
    }

    private void exportCsv() {
        CsvUtils.exportStudents("notes.csv", students);
        System.out.println("CSV enregistré.");
    }
}



