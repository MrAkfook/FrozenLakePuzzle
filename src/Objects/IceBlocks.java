package Objects;

public class IceBlocks extends Hazard{
    
    public IceBlocks(){
        this(-1);
    }

    public IceBlocks(int id){
        super(id);
    }

    public IceBlocks(IceBlocks other){
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
