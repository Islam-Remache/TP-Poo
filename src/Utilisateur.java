/*
    * fixer la periode (date de debut et date de fin)
 */

public class Utilisateur {
    String pseudo;
    Calendrier calendrier;

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
        this.calendrier = new Calendrier();
    }
    public void afficher() {
        System.out.println("Utilisateur: " + this.pseudo);
        this.calendrier.afficher();
    }



}
