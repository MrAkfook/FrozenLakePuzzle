package Exceptions;

public class IncorrectBagContentsException extends Exception{
    public IncorrectBagContentsException() {
        super("*** Researchers cannot carry different types of equipment in their bags.");
    }
    public IncorrectBagContentsException(String message) {
        super(message);
    }
}
