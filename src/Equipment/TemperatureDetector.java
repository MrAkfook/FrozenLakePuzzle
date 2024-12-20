package Equipment;

public class TemperatureDetector extends ResearchEquipment{

    private int temperature; // temperature in degrees Celsius -30 to 0

    public TemperatureDetector(int id) {
        super(id);
        this.temperature = (int) (Math.random() * 31) - 30; // random temperature between -30 and 0
    }

    public TemperatureDetector(){
        this(-1);
    }

    public TemperatureDetector(TemperatureDetector other){
        super(other);
        this.temperature = other.temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String report() {
        return "Temperature Detector: " + temperature + " degrees Celsius";
    }

    @Override
    public String showOnMap() {
        return "TD";
    }

    @Override
    public boolean equals(Object other) {
        if (super.equals(other)){
            TemperatureDetector otherTemperatureDetector = (TemperatureDetector) other;
            return this.temperature == otherTemperatureDetector.temperature;
        }
        else{
            return false;
        }
    }

}
