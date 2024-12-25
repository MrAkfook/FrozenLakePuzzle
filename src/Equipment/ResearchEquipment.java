package Equipment;

import Objects.Experiment;

public abstract class ResearchEquipment extends Equipment{


    public ResearchEquipment(int id) {
        super(id);

    }

    public ResearchEquipment(){
        this(-1);
    }

    public ResearchEquipment(ResearchEquipment other){
        super(other);
    }

    public abstract String report();

    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }

    public abstract Experiment getExperiment();

}
