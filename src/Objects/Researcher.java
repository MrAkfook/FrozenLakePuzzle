package Objects;

import Bag.EquipmentBag;
import Equipment.Equipment;
import Exceptions.IncorrectBagContentsException;
import Interfaces.IMapPlaceable;

public class Researcher implements IMapPlaceable{
    private EquipmentBag<Equipment> equipmentBag;
    private String id;

    public Researcher(String id){
        equipmentBag = new EquipmentBag<Equipment>();
        this.id = id;
    }

    public Researcher(){
        this("");
    }

    public boolean takeEquipment(Equipment equipment) throws IncorrectBagContentsException {
        return equipmentBag.add(equipment);
    }

    public String getId(){
        return id;
    }

    public String showOnMap(){
        return id;
    }
}
