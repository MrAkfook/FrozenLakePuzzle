package Exceptions;

public class UnavailableDirectionException extends Exception{
    public UnavailableDirectionException() {
        super("*** The input direction is unavailable. Please enter an available direction:");
    }
    public UnavailableDirectionException(String message) {
        super(message);
    }
}
