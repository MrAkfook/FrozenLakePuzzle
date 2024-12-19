package Equipment;

public class ChiselingEquipment extends ResearchEquipment{

    private double iceBlockWeight;

    public ChiselingEquipment() {
        super();
        this.iceBlockWeight = Math.random() * 20; // Random ice block weight between 0 and 20
    }

    public ChiselingEquipment(ChiselingEquipment other){
        super();
        this.iceBlockWeight = other.iceBlockWeight;
    }

    public double getIceBlockWeight() {
        return iceBlockWeight;
    }

    public String report() {
        return "Ice Block Weight: " + iceBlockWeight + " kg";
    }

    @Override
    public String showOnMap() {
        return "CH";
    }

    
}
