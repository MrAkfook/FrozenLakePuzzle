package Equipment;

public class WindSpeedDetector extends ResearchEquipment {

    private int windSpeed;

    public WindSpeedDetector(int id) {
        super(id);
        this.windSpeed = (int) Math.round(Math.random() * 30); // Random wind speed between 0 and 30, rounded to the nearest integer
    }

    public WindSpeedDetector(){
        this(-1);
    }

    public WindSpeedDetector(WindSpeedDetector other){
        super(other);
        this.windSpeed = other.windSpeed;
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

    @Override
    public boolean equals(Object other) {
        if (super.equals(other)){
            WindSpeedDetector otherWindSpeedDetector = (WindSpeedDetector) other;
            return this.windSpeed == otherWindSpeedDetector.windSpeed;
        }
        else{
            return false;
        }
    }

}
