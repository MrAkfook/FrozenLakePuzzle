package Equipment;

public class Camera extends ResearchEquipment{

    private boolean isWorking;

    public Camera() {
        super();
        this.isWorking = Math.random() >= 0.2;
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
