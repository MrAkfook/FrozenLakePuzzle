package Lake;

import Interfaces.IMapPlaceable;
import Objects.Researcher;
import java.util.ArrayList;
import java.util.List;


public class LakeSquare {

    private Researcher researcher;
    private IMapPlaceable MapItem;

    public LakeSquare(){
        this(null, null);
    }

    public LakeSquare(LakeSquare other){
        this.researcher = other.researcher;
        this.MapItem = other.MapItem;
    }

    public LakeSquare(Researcher researcher, IMapPlaceable MapItem){
        this.researcher = researcher;
        this.MapItem = MapItem;
    }

    public Researcher getResearcher() {
        return new Researcher(researcher);
    }

    public IMapPlaceable getPriorityObject() {
        return (researcher == null) ? MapItem : researcher;
    }

    public void setObject(IMapPlaceable MapItem) {
        if (MapItem instanceof Researcher){
            this.researcher = (Researcher) MapItem;
        }
        else {
            this.MapItem = MapItem;
        }
    }

    public List<IMapPlaceable> getAllObjects() {
        List<IMapPlaceable> objects = new ArrayList<IMapPlaceable>();
        if (researcher != null) objects.add(researcher);
        if (MapItem != null) objects.add(MapItem);
        return objects;
    }

    public boolean equals(Object other){
        if (other == null) return false;
        if (this == other) return true;
        if (this.getClass() != other.getClass()) return false;
        LakeSquare otherSquare = (LakeSquare) other;
        return this.researcher.equals(otherSquare.researcher) && this.MapItem.equals(otherSquare.MapItem);
    }

    public String toString(){
        return ((researcher == null) ? ""    :   "R"+researcher.getId()  ) + ((MapItem == null) ? ""    :   MapItem.toString() );
    }

}
