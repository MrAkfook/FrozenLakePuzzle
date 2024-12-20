package Equipment;

public class LargeWoodenBoard extends HazardEquipment{

    public LargeWoodenBoard() {
        super();
    }

    public LargeWoodenBoard(int id) {
        super(id);
    }

    public LargeWoodenBoard(ProtectiveHelmet other){
        super(other);
    }

    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return "LWB";
    }


}
