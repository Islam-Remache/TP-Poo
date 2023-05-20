import java.sql.Date;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import Exeption.CreneauLibreLessThanMinException;
import Exeption.CreneauLibreOverlappingException;
import Exeption.NoAvailableTimeSlotException;

import java.time.*;
import java.util.List;


public class Calendrier {
    TreeSet<Journee> journees;
    ArrayList<Projet> projets;
    ArrayList<Tache> unscheduledTaches;
    List<String> dayNames = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");


    LocalDate today = LocalDate.now();
    LocalDate sunday = today.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
    public Calendrier() {
        this.journees = new TreeSet<Journee>();
        this.projets = new ArrayList<Projet>();
        this.unscheduledTaches = new ArrayList<Tache>();
        for (int i = 0; i < 7; i++) {
            String dayName = dayNames.get(i);
            Date date = Date.valueOf(sunday.plusDays(i));
            Journee journee = new Journee(date, dayName);
            this.journees.add(journee);
        }
    }
    // print the calendar
    public void afficher() {
        // we want to use an iterator to iterate over the days
        for (Journee journee : journees) {
            System.out.println(journee.nom + " " + journee.date);
            for (Creneau creneau : journee.creneaux.values()) {
                System.out.println(creneau.debut + " " + creneau.fin);
            }
        }

    }
    // add unscheduled taches to the calendar
    public void addUnscheduledTaches(ArrayList<Tache> taches) {
        this.unscheduledTaches.addAll(taches);
    }


    
public void PlanifierTacheSimple(Tache tache) throws NoAvailableTimeSlotException,CreneauLibreLessThanMinException,CreneauLibreOverlappingException{
    Creneau creneau = null;
    for(Journee journee : journees){
       if(journee.date.compareTo(tache.Deadline)<=0){
            creneau = journee.trouverCreneauDisponible(tache.duree);
            if(creneau != null){
                creneau.addTache(tache);
                if((tache.duree<creneau.getDuree())){
                    LocalTime updatedDebut = creneau.debut;
                    updatedDebut.plusSeconds(tache.duree);
                    LocalTime updatedFin = creneau.fin;
                    creneau.fin = creneau.debut.plusMinutes( tache.duree);
                    try{
                    journee.addCreneau(updatedDebut,updatedFin);
                    }catch(CreneauLibreLessThanMinException e){
                        System.out.println("Extra Time has been deleted");
                    }
                    break;
                }
            }
    
        }else{
             this.unscheduledTaches.add(tache);
             tache.etat = Etat.notRealized;
             throw new NoAvailableTimeSlotException();

        }
    }

    if(creneau == null){
        this.unscheduledTaches.add(tache);
        tache.etat = Etat.notRealized;
        throw new NoAvailableTimeSlotException();
    }
}

}


