import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Exeption.*;
/*
 * incrementer le nombre de tache a realiser ? ========= ++in tache
 */


public class Journee implements Comparable<Journee>{
    String nom;
    TreeMap<LocalTime, Creneau> creneaux;
    Date date;
    int nbTacheARealiser;
    int nbTacheRealisee;

    public Journee(Date date , String nom) {
        this.nom = nom;
        this.date = date;
        this.creneaux = new TreeMap<LocalTime, Creneau>();
        this.nbTacheARealiser = 0;
        this.nbTacheRealisee = 0;
    }

    public void setTacheARealiser(int nbTacheARealiser) {
        this.nbTacheARealiser = nbTacheARealiser;
    }

    // add a creneau libre to the day , the creneau libre must be at least 30 min and not overlapping with an existing one
    public void addCreneau(LocalTime debut, LocalTime fin) throws CreneauLibreLessThanMinException, CreneauLibreOverlappingException {
        Creneau creneau = new Creneau(debut, fin);
        if (isCreneauOverlapping(creneau)) {
            throw new CreneauLibreOverlappingException();
        }
        creneaux.put(debut, creneau);
    }

    public boolean isCreneauOverlapping(Creneau creneau) {
        Map.Entry<LocalTime, Creneau> beforeEntry = creneaux.lowerEntry(creneau.debut);// return the creneau before the creneau to add
        Map.Entry<LocalTime, Creneau> afterEntry = creneaux.higherEntry(creneau.debut);// return the creneau after the creneau to add
        // we check if the creneau to add is overlapping with the creneau before or after
        if (beforeEntry != null && creneau.debut.isBefore(beforeEntry.getValue().fin)) {
            return true;
        }
        if (afterEntry != null && creneau.fin.isAfter(afterEntry.getValue().debut)) {
            return true;
        }
        return false;
    }

    private Creneau trouverCreneauDisponible(long duree) {
        for (Creneau creneau : creneaux.values()) {
            if (creneau.isLibre && creneau.getDuree() >= duree) {
                return creneau;
            }
        }
        return null;
    }
    // get all the taches of the day
    public ArrayList<Tache> getTaches() {
        ArrayList<Tache> taches = new ArrayList<>();
        for (Creneau creneau : creneaux.values()) {
            if (!creneau.isLibre) {
                taches.add(creneau.tache);
            }
        }
        return taches;
    }



    // planifier
    public ArrayList<Tache> decomposer(TachDecomposable tache) throws NoAvailableTimeSlotException {
        ArrayList<Tache> sousTaches = new ArrayList<>();
        long duree = tache.duree;
        Etat etat = tache.etat;
        String nom = tache.nom;
        Priorite priorite = tache.priorite;
        long remainingTime = duree;
        int subTacheNum = 1;
        while (remainingTime > 0) {
            Creneau creneau = trouverCreneauDisponible(remainingTime);
            if (creneau == null) {
                Creneau firstLibreCreneau = null;
                // If there is no free time slot long enough, split the task into two sub-tasks
                for (Creneau c : creneaux.values()) {
                    if (c.isLibre) {
                        firstLibreCreneau = c;
                        break;
                    }
                }
                if (firstLibreCreneau == null) {
                    // If there are no free time slots, throw an exception
                    throw new NoAvailableTimeSlotException();
                }
                long creneauDuree = firstLibreCreneau.getDuree();
                TachSimple sousTache1 = new TachSimple(nom + " " + subTacheNum, creneauDuree, priorite, etat, 0);
                firstLibreCreneau.addTache(sousTache1);
                sousTaches.add(sousTache1);
                remainingTime -= creneauDuree;
            } else {
                // If there is an available time slot, create a sub-task with the duration of the time slot
                TachSimple sousTache = new TachSimple(nom + " " + subTacheNum, creneau.getDuree(), priorite, etat, 0);
                creneau.addTache(sousTache);
                sousTaches.add(sousTache);
                remainingTime -= creneau.getDuree();
            }
            subTacheNum++;
        }
        return sousTaches;
    }

    @Override
    public int compareTo(Journee journee) {
        return this.date.compareTo(journee.date);
    }


}