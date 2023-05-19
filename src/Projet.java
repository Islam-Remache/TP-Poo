import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
/*
    * A evaluer ?
 */
public class Projet {
     String nom;
     String description;
     ArrayList<Tache> taches;
     Etat etat;
     Date dateLimite;

    public Projet(String nom, String description, ArrayList<Tache> taches, Etat etat, Date dateLimite) {
        this.nom = nom;
        this.description = description;
        this.taches = taches;
        this.etat = etat;
        this.dateLimite = dateLimite;
    }
    // add multiple taches to the projet
    public void addTaches(ArrayList<Tache> taches) {
        this.taches.addAll(taches);
    }

}
