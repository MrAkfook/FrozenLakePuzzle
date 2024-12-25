package Objects;

import Bag.EquipmentBag;
import Equipment.Equipment;
import Exceptions.IncorrectBagContentsException;
import Exceptions.UnavailableEquipmentException;
import Interfaces.IMapPlaceable;

public class Researcher implements IMapPlaceable{
    private EquipmentBag equipmentBag;
    private int id;

    public Researcher(int id){
        equipmentBag = new EquipmentBag();
        this.id = id;
    }

    public Researcher(){
        this(-1);
    }

    public Researcher(Researcher other){
        this.equipmentBag = new EquipmentBag(other.equipmentBag);
        this.id = other.id;
    }

    public boolean takeEquipment(Equipment equipment) throws IncorrectBagContentsException {
        return equipmentBag.add(equipment);
    }

    public Equipment useEquipment(Equipment equipment){
        for(Equipment e :equipmentBag.toArray()){
            if (e.getClass().equals(equipment.getClass()))
                equipmentBag.remove(e);
                return e;
        }
        return null;
    }

    public Equipment[] getEquipmentBagArray(){
        return equipmentBag.toArray();
    }

    public boolean carryingResearchEquipment() throws UnavailableEquipmentException {
        if(equipmentBag.hasResearchEquipment()){
            return true;
        }
        throw new UnavailableEquipmentException("*** Researcher currently doesn't have any Research Equipment.");
    }

    public int getId(){
        return id;
    }


    public String showOnMap(){
        return ("R"+id);
    }
}
