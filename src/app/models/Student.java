package app.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private String name;
    private Map<String, Double> notes;

    public Student(String name) {
        this.name = name;
        this.notes = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getNotes() {
        return notes;
    }

    public void addNote(String module, double note) {
        notes.put(module, note);
    }

    public double calculateAverage() {
        if (notes.isEmpty()) return 0;

        double sum = 0;
        for (double n : notes.values()) {
            sum += n;
        }
        return sum / notes.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Etudiant: " + name + "\n");
        for (Map.Entry<String, Double> e : notes.entrySet()) {
            sb.append("  - ").append(e.getKey())
                    .append(" : ").append(e.getValue()).append("\n");
        }
        sb.append("  => Moyenne: ")
                .append(String.format("%.2f", calculateAverage()))
                .append("\n");
        return sb.toString();
    }
}





