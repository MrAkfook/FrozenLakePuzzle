package Lake;

import Interfaces.IMapPlaceable;
import Objects.Researcher;

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

    public void setResearcher(Researcher researcher) {
        this.researcher = researcher;
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







}
