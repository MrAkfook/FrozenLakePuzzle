package Objects;

public class IceBlock extends Hazard{
    
    public IceBlock(){
        this(-1);
    }

    public IceBlock(int id){
        super(id);
    }

    public IceBlock(IceBlock other){
        super(other);
    }


    @Override
    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return "IB";
    }
}
