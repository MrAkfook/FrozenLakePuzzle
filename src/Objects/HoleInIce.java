package Objects;

import Equipment.LargeWoodenBoard;
import Interfaces.IDangerousHazard;

public class HoleInIce extends Hazard implements IDangerousHazard{
    
    public HoleInIce(){ 
        this(-1);
    }

    public HoleInIce(int id){
        super(id);
        setOvercomeBy(new LargeWoodenBoard());
    }

    public HoleInIce(HoleInIce other){
        super(other);
    }

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return "HI";
    }
}
