package Equipment;

import Interfaces.IMapPlaceable;

public abstract class ResearchEquipment extends Equipment implements IMapPlaceable {


    public ResearchEquipment(int id) {
        super(id);

    }

    public ResearchEquipment(){
        this(-1);
    }

    public ResearchEquipment(ResearchEquipment other){
        super(other);
    }

    public abstract String report();

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }

}
