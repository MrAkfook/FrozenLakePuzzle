package Objects;

import Equipment.HazardEquipment;

public abstract class Hazard {
    HazardEquipment overcomeBy;
    public Hazard(){
        overcomeBy = null;
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
            return true;
        }      
    }
}
