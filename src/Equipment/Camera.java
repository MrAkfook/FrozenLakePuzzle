package Equipment;

public class Camera extends ResearchEquipment{

    private boolean isWorking;

    public Camera() {
        super();
        this.isWorking = Math.random() >= 0.2;
    }
    
    public Camera(Camera other){
        super();
        this.isWorking = other.isWorking;
    }

    public boolean isWorking() {
        return this.isWorking;
    }

    public String report() {
        return "Camera is working: " + this.isWorking;
    }

    public String showOnMap() {
        return "CM";
    }
    
}
