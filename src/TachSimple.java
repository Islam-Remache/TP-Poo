public class TachSimple extends Tache{
    int periodicite;
    public TachSimple(String nom, long duree, Priorite priorite, Etat etat, int periodicite) {
        super(nom, duree, priorite, etat);
        this.periodicite = periodicite;
    }
    public boolean isRealised() {
        return this.etat.equals(Etat.realized);
    }
}
