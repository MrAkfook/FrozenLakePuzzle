package Equipment;

import java.net.ProtocolException;

public class ProtectiveHelmet extends HazardEquipment{

    public ProtectiveHelmet() {
        super();
    }

    public ProtectiveHelmet(int id) {
        super(id);
    }

    public ProtectiveHelmet(ProtectiveHelmet other){
        super(other);
    }

    public boolean equals(Object other){
        return super.equals(other);
    }

    @Override
    public String showOnMap() {
        return "PH";
    }
}
