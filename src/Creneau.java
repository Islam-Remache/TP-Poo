import Exeption.CreneauLibreLessThanMinException;

import java.sql.Time;
/*
    * bloquer un creneau
    * la durée d'un creneau est de 30 minutes
 */

public class Creneau implements Comparable<Creneau> {
    boolean isLibre;
    Time debut;
    Time fin;
    Tache tache;
    public Creneau(Time debut, Time fin) throws CreneauLibreLessThanMinException {
        this.debut = debut;
        this.fin = fin;
        this.isLibre = true;
        this.tache = null;
        if (fin.getTime() - debut.getTime() < 1800000) {
            throw new CreneauLibreLessThanMinException();
        }
    }
    public void addTache(Tache tache) {
        this.tache = tache;
        isLibre = false;
    }
    // getDuree
    public long getDuree() {
        return fin.getTime() - debut.getTime();
    }

    @Override
    public int compareTo(Creneau creneau) {
        return Long.compare(this.debut.getTime(), creneau.debut.getTime());
    }

}
