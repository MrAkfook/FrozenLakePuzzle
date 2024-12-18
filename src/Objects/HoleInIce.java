package Objects;

import Equipment.LargeWoodenBoard;
import Interfaces.IDangerousHazard;

public class HoleInIce extends Hazard implements IDangerousHazard{
    public HoleInIce(){ 
        super();
        setOvercomeBy(new LargeWoodenBoard());
    }
    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }
}
