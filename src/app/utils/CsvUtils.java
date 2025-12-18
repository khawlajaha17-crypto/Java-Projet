package app.utils;

import app.models.Student;

import java.io.*;
import java.util.*;

public class CsvUtils {

    public static List<Student> importStudents(String file) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine();
            if (header == null) return students;

            String[] modules = header.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Student s = new Student(data[0]);

                for (int i = 1; i < data.length; i++) {
                    if (!data[i].isEmpty()) {
                        s.addNote(modules[i],
                                Double.parseDouble(data[i].replace(",", ".")));
                    }
                }
                students.add(s);
            }
        } catch (Exception e) {
            System.out.println("Fichier CSV introuvable ou invalide (premier lancement).");
        }
        return students;
    }

    public static void exportStudents(String file, List<Student> students) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            Set<String> modules = new LinkedHashSet<>();
            for (Student s : students) {
                modules.addAll(s.getNotes().keySet());
            }

            bw.write("etudiant");
            for (String m : modules) bw.write("," + m);
            bw.newLine();

            for (Student s : students) {
                bw.write(s.getName());
                for (String m : modules) {
                    Double note = s.getNotes().get(m);
                    bw.write("," + (note != null ? note : ""));
                }
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error exporting CSV.");
        }
    }
}



