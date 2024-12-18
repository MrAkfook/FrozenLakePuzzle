package Equipment;

public class ChiselingEquipment extends ResearchEquipment{

    private double iceBlockWeight;

    public ChiselingEquipment() {
        super();
        this.iceBlockWeight = Math.random() * 20; // Random ice block weight between 0 and 20
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        
        return true;
    }
}
