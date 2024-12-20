package Objects;

import Equipment.ProtectiveHelmet;
import Interfaces.IDangerousHazard;

public class IceSpikes extends Hazard implements IDangerousHazard{

    public IceSpikes(){
        this(-1);
    }

    public IceSpikes(int id){
        super(id);
        setOvercomeBy(new ProtectiveHelmet());
    }

    public IceSpikes(IceSpikes other){
        super(other);
    }

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return "IS";
    }
}
