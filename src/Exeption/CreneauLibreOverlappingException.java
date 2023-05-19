package Exeption;

public class CreneauLibreOverlappingException extends Exception{
    public CreneauLibreOverlappingException() {
            super("Creneau libre overlapping with an existing one");
        }
}
