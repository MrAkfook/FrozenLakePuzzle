package Objects;

import Equipment.HazardEquipment;
import Interfaces.IMapPlaceable;

public abstract class Hazard implements IMapPlaceable{

    HazardEquipment overcomeBy;
    private int id;

    public Hazard(){
        this(-1);
    }

    public Hazard(int id){
        this.id = id;
        overcomeBy = null;
    }

    public Hazard(Hazard other){
        this.id = other.id;
        this.overcomeBy = other.overcomeBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HazardEquipment getOvercomeBy() {
        return overcomeBy;
    }

    public void setOvercomeBy(HazardEquipment overcomeBy) {
        this.overcomeBy = overcomeBy;
    }
    
    @Override
    public boolean equals(Object other){
        // Check if the current object is the same as the passed object
        if (this == other) {
            return true;
        }
        // Check if the passed object is null or if the classes of the objects are different
        if (other == null || getClass() != other.getClass()){
            return false;
        }
        else {
            // Cast the passed object to a Hazard object
            Hazard otherHazard = (Hazard) other;
            return otherHazard.getId() == this.id;
        }      
    }

    public abstract String showOnMap();
}
