import java.util.ArrayList;
import java.sql.Date;

public class TachDecomposable extends Tache{
    ArrayList<Tache> subTaches;

    public TachDecomposable(String nom, int duree, Priorite priorite, Etat etat,Date Deadline) {
        super(nom, duree, priorite, etat,Deadline);
        this.subTaches = new ArrayList<Tache>();
    }
    public boolean isRealised() {
        // we see if the etat of all the subTaches is done
        for (Tache tache : subTaches) {
            if (!tache.etat.equals(Etat.realized)) {
                return false;
            }
        }
        return true;
    }
}