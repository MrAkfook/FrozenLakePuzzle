package Objects;

import Equipment.LargeWoodenBoard;
import Interfaces.IDangerousHazard;

public class HoleInIce extends Hazard implements IDangerousHazard{
    public HoleInIce(){ 
        super();
        setOvercomeBy(new LargeWoodenBoard());
    }

    public HoleInIce(HoleInIce other){
        super();
        setOvercomeBy(other.getOvercomeBy());
    }

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }
}
