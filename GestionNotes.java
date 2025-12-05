import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GestionNotes {
    private List<Etudiant> etudiants;
    
    public GestionNotes() {
        this.etudiants = new ArrayList<>();
    }
    
    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
    }
    
    public void supprimerEtudiant(String matricule) {
        etudiants.removeIf(e -> e.getMatricule().equals(matricule));
    }
    
    public Etudiant trouverEtudiant(String matricule) {
        return etudiants.stream()
                .filter(e -> e.getMatricule().equals(matricule))
                .findFirst()
                .orElse(null);
    }
    
    // Export vers CSV
    public void exporterCSV(String nomFichier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            // En-tête
            writer.println("Matricule,Nom,Prenom,Matiere,Note,Moyenne");
            
            // Données
            for (Etudiant etudiant : etudiants) {
                for (Map.Entry<String, Double> entry : etudiant.getNotes().entrySet()) {
                    String ligne = String.format("%s,%s,%s,%s,%.2f,%.2f",
                            etudiant.getMatricule(),
                            etudiant.getNom(),
                            etudiant.getPrenom(),
                            entry.getKey(),
                            entry.getValue(),
                            etudiant.getMoyenne());
                    writer.println(ligne);
                }
            }
            System.out.println("Export CSV réussi vers: " + nomFichier);
            
        } catch (IOException e) {
            System.err.println("Erreur lors de l'export CSV: " + e.getMessage());
        }
    }
    
    // Import depuis CSV
    public void importerCSV(String nomFichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomFichier))) {
            String ligne;
            boolean premiereLigne = true;
            
            while ((ligne = reader.readLine()) != null) {
                if (premiereLigne) {
                    premiereLigne = false;
                    continue; // Ignorer l'en-tête
                }
                
                String[] donnees = ligne.split(",");
                if (donnees.length >= 5) {
                    String matricule = donnees[0];
                    String nom = donnees[1];
                    String prenom = donnees[2];
                    String matiere = donnees[3];
                    double note = Double.parseDouble(donnees[4]);
                    
                    // Chercher ou créer l'étudiant
                    Etudiant etudiant = trouverEtudiant(matricule);
                    if (etudiant == null) {
                        etudiant = new Etudiant(nom, prenom, matricule);
                        ajouterEtudiant(etudiant);
                    }
                    
                    etudiant.ajouterNote(matiere, note);
                }
            }
            System.out.println("Import CSV réussi depuis: " + nomFichier);
            
        } catch (IOException e) {
            System.err.println("Erreur lors de l'import CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur de format de nombre: " + e.getMessage());
        }
    }
    
    // Afficher tous les étudiants
    public void afficherEtudiants() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant dans la base de données.");
            return;
        }
        
        System.out.println("\n=== LISTE DES ÉTUDIANTS ===");
        etudiants.forEach(System.out::println);
    }
    
    // Afficher les étudiants par ordre de moyenne
    public void afficherParMoyenne() {
        List<Etudiant> tries = etudiants.stream()
                .sorted((e1, e2) -> Double.compare(e2.getMoyenne(), e1.getMoyenne()))
                .collect(Collectors.toList());
        
        System.out.println("\n=== ÉTUDIANTS PAR ORDRE DE MOYENNE ===");
        tries.forEach(e -> System.out.printf("%s - Moyenne: %.2f\n", 
                e.getPrenom() + " " + e.getNom(), e.getMoyenne()));
    }
    
    // Statistiques
    public void afficherStatistiques() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucune statistique disponible.");
            return;
        }
        
        double moyenneGenerale = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .average()
                .orElse(0.0);
        
        double meilleureMoyenne = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .max()
                .orElse(0.0);
        
        double pireMoyenne = etudiants.stream()
                .mapToDouble(Etudiant::getMoyenne)
                .min()
                .orElse(0.0);
        
        System.out.println("\n=== STATISTIQUES ===");
        System.out.printf("Nombre d'étudiants: %d\n", etudiants.size());
        System.out.printf("Moyenne générale: %.2f\n", moyenneGenerale);
        System.out.printf("Meilleure moyenne: %.2f\n", meilleureMoyenne);
        System.out.printf("Pire moyenne: %.2f\n", pireMoyenne);
    }
}
