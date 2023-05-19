public abstract class Tache {
    String nom;
    long duree;
    Priorite priorite;
    Etat etat;

    public Tache(String nom, long duree, Priorite priorite, Etat etat) {
        this.nom = nom;
        this.duree = duree;
        this.priorite = priorite;
        this.etat = etat;
    }
    abstract boolean isRealised();

}
