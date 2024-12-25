package Equipment;

import Objects.Experiment;

public class Camera extends ResearchEquipment{

    private boolean isWorking;

    public Camera(int id) {
        super(id);
        this.isWorking = Math.random() >= 0.2;
    }

    public Camera(){
        this(-1);
    }

    public Camera(Camera other){
        super(other);
        this.isWorking = other.isWorking;
    }

    public boolean isWorking() {
        return this.isWorking;
    }

    public String report() {
        return "Camera Placement: " + ((this.isWorking) ? "The camera successfully started recording.":"The camera failed to start recording.");
    }

    public String showOnMap() {
        return "CM";
    }
    
    @Override
    public boolean equals(Object other){
        if (super.equals(other)){
            Camera otherCamera = (Camera) other;
            return this.isWorking == otherCamera.isWorking;
    }
        else{return false;}
    } 

    @Override
    public Experiment getExperiment() {
        return Experiment.CAMERA_PLACEMENT;
    }
    
}
