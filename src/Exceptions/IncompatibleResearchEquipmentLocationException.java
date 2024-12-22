package Exceptions;

public class IncompatibleResearchEquipmentLocationException extends Exception{
    public IncompatibleResearchEquipmentLocationException() {
        super("*** The selected research equipment is incompatible with the current location.");
    }
    public IncompatibleResearchEquipmentLocationException(String message){
        super(message);
    }

}
