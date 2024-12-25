package Objects;

import Equipment.ClimbingEquipment;
import Interfaces.IDangerousHazard;

public class CliffEdge extends Hazard implements IDangerousHazard{
    public CliffEdge(){
        super();
        setOvercomeBy(new ClimbingEquipment());
    }
    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }
    @Override
    public String showOnMap() {
        return "CE";
    }
}
