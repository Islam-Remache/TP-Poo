import java.util.ArrayList;

public class Categorie {
    String nom;
    ArrayList<Tache> taches;
    String Color;

    public Categorie(String nom, String Color) {
        this.nom = nom;
        this.taches = new ArrayList<Tache>();
        this.Color = Color;
    }
    public void addTache(Tache tache) {
        this.taches.add(tache);
    }

}
