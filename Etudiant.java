import java.util.HashMap;
import java.util.Map;

public class Etudiant {
    private String nom;
    private String prenom;
    private String matricule;
    private Map <String, Double> notes; // Matière -> Note
    
    public Etudiant(String nom, String prenom, String matricule) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.notes = new HashMap <>();
    }
    
    // Getters et Setters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getMatricule() { return matricule; }
    public Map <String, Double> getNotes() { return notes; }
    
    public void ajouterNote(String matiere, double note) {
        notes.put(matiere, note);
    }
    
    public double getMoyenne() {
        if (notes.isEmpty()) return 0.0;
        return notes.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (Matricule: %s) - Moyenne: %.2f", 
                           prenom, nom, matricule, getMoyenne());
    }
}