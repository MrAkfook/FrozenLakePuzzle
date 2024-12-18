package Objects;

import Equipment.ProtectiveHelmet;
import Interfaces.IDangerousHazard;

public class IceSpikes extends Hazard implements IDangerousHazard{

    public IceSpikes(){
        super();
        setOvercomeBy(new ProtectiveHelmet());
    }
    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }
}
