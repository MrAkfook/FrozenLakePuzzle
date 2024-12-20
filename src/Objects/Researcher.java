package Objects;

import Bag.EquipmentBag;
import Equipment.Equipment;
import Exceptions.IncorrectBagContentsException;
import Interfaces.IMapPlaceable;

public class Researcher implements IMapPlaceable{
    private EquipmentBag<Equipment> equipmentBag;
    private int id;

    public Researcher(int id){
        equipmentBag = new EquipmentBag<Equipment>();
        this.id = id;
    }

    public Researcher(){
        this(-1);
    }

    public boolean takeEquipment(Equipment equipment) throws IncorrectBagContentsException {
        return equipmentBag.add(equipment);
    }

    public boolean useEquipment(Equipment equipment){
        return equipmentBag.remove(equipment);
    }

    public EquipmentBag<Equipment> getEquipmentBag(){
        return null; //TODO: implement
    }

    public boolean carryingResearchEquipment(){
        return equipmentBag.hasResearchEquipment();
    }

    public int getId(){
        return id;
    }


    public String showOnMap(){
        return ("R"+id);
    }
}
