package Equipment;

public abstract class HazardEquipment extends Equipment{

    public HazardEquipment(int id) {
        super(id);
    }

    public HazardEquipment(){
        super();
    }

    public HazardEquipment(HazardEquipment other){
        super(other);
    }

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }


}
