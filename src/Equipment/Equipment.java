package Equipment;

public abstract class Equipment {
    private int id;

    public Equipment(int id){
        this.id = id;
    } 

    public Equipment(){
        this(-1);
    }

    public Equipment(Equipment other){
        this(other.id);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public abstract String showOnMap();

    @Override
    public boolean equals(Object other){
        // Check if the current object is the same as the passed object
        if (this == other) {
            return true;
        }
        // Check if the passed object is null or if the classes of the objects are different
        if (other == null || getClass() != other.getClass()){
            return false;
        }
        else {
            Equipment otherEquipment = (Equipment) other;
            return this.id == otherEquipment.id;
        }      
    }
}
