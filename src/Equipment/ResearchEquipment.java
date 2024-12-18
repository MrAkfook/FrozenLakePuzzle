package Equipment;

import Interfaces.IMapPlaceable;

public abstract class ResearchEquipment extends Equipment implements IMapPlaceable {


    public ResearchEquipment() {
        super();

    }

    public abstract String showOnMap();
    public abstract String report();

}
