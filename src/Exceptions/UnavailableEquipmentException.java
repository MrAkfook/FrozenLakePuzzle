package Exceptions;

public class UnavailableEquipmentException extends Exception{
    public UnavailableEquipmentException() {
        super("");
    }
    public UnavailableEquipmentException(String message) {
        super(message);
    }
}
