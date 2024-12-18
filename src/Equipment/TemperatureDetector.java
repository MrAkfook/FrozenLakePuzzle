package Equipment;

public class TemperatureDetector extends ResearchEquipment{

    private int temperature; // temperature in degrees Celsius -30 to 0

    public TemperatureDetector() {
        super();
        this.temperature = (int) (Math.random() * 31) - 30; // random temperature between -30 and 0
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

}
