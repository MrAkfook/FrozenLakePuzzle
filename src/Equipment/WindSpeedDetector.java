package Equipment;

public class WindSpeedDetector extends ResearchEquipment {

    private double windSpeed;

    public WindSpeedDetector() {
        super();
        this.windSpeed = Math.random() * 30; // Random wind speed between 0 and 30
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String report() {
        return "Wind Speed: " + windSpeed + " m/s";
    }

    @Override
    public String showOnMap() {
        return "WS";
    }

}
