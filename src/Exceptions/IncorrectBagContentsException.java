package Exceptions;

public class IncorrectBagContentsException extends Exception{
    public IncorrectBagContentsException(String message) {
        super(message);
    }

    public IncorrectBagContentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
