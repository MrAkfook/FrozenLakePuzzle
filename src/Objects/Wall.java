package Objects;

import Equipment.HazardEquipment;
import Interfaces.IMapPlaceable;

public class Wall implements IMapPlaceable{

    int id;

    public Wall(int id) {
        this.id = id;
    }

    public Wall(){
        this.id = 0;
    }

    public Wall(Wall other){
        this(other.id);
    }

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return ""; 
    }

}
