package Exeption;

public class CreneauLibreLessThanMinException extends Exception{
    public CreneauLibreLessThanMinException() {
        super("Creneau libre less than 30 min");
    }
}
