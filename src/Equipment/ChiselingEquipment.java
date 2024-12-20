package Equipment;

public class ChiselingEquipment extends ResearchEquipment{

    private double iceBlockWeight;

    public ChiselingEquipment(int id) {
        super(id);
        this.iceBlockWeight = (Math.random() * 19) + 1 ; // Random ice block weight between 1 and 20
    }

    public ChiselingEquipment(){
        this(-1);
    }

    public ChiselingEquipment(ChiselingEquipment other){
        super(other);
        this.iceBlockWeight = other.iceBlockWeight;
    }

    public double getIceBlockWeight() {
        return iceBlockWeight;
    }

    public String report() {
        return String.format("Ice Block Weight: %.2f g", iceBlockWeight);
    }

    @Override
    public String showOnMap() {
        return "CH";
    }

    @Override
    public boolean equals(Object other) {
        if (super.equals(other)){
            ChiselingEquipment otherChiselingEquipment = (ChiselingEquipment) other;
            return this.iceBlockWeight == otherChiselingEquipment.iceBlockWeight;
        }
        else{
            return false;
        }
    }

    
}
