import java.sql.Date;
public abstract class Tache {
    String nom;
    long duree;
    Priorite priorite;
    Etat etat;
    Date Deadline;

    public Tache(String nom, long duree, Priorite priorite, Etat etat,Date Deadline) {
        this.nom = nom;
        this.duree = duree;
        this.priorite = priorite;
        this.etat = etat;
        this.Deadline = Deadline;
    }
    abstract boolean isRealised();

}
