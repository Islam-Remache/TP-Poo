package Exeption;

public class NoAvailableTimeSlotException extends Exception{
    public NoAvailableTimeSlotException() {
        super("No available time slot");
    }
}
