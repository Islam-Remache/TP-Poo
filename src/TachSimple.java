import java.sql.Date;

public class TachSimple extends Tache{
    int periodicite;
    public TachSimple(String nom, long duree, Priorite priorite, Etat etat, int periodicite,Date Deadline) {
        super(nom, duree, priorite, etat,Deadline);
        this.periodicite = periodicite;
    }
    public boolean isRealised() {
        return this.etat.equals(Etat.realized);
    }
}
