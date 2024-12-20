package Equipment;

public class ClimbingEquipment extends HazardEquipment{

    public ClimbingEquipment() {
        super();
    }

    public ClimbingEquipment(int id) {
        super(id);
    }

    public ClimbingEquipment(ClimbingEquipment other){
        super(other);
    }

    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return "CE";
    }

}
