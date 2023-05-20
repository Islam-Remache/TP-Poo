import Exeption.CreneauLibreLessThanMinException;

import java.time.LocalTime;
/*
    * bloquer un creneau
    * la durée d'un creneau est de 30 minutes
 */

public class Creneau implements Comparable<Creneau> {
    boolean isLibre;
    LocalTime debut;
    LocalTime fin;
    Tache tache;
    public Creneau(LocalTime debut, LocalTime fin) throws CreneauLibreLessThanMinException {
        this.debut = debut;
        this.fin = fin;
        this.isLibre = true;
        this.tache = null;
        if (fin.isBefore(debut.plusMinutes(30))) {
            throw new CreneauLibreLessThanMinException();
        }
    }
    public void addTache(Tache tache) {
        this.tache = tache;
        isLibre = false;
    }
    // getDuree
    public long getDuree() {
        return fin.toSecondOfDay() - debut.toSecondOfDay();
    }

    @Override
    public int compareTo(Creneau creneau) {
        return this.debut.compareTo(creneau.debut);
    }

}
