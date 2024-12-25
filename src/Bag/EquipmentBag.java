package Bag;

import Equipment.*;
import Exceptions.*;

import java.util.HashSet;
import java.util.Set;

public class EquipmentBag{

    Set<Equipment> set = new HashSet<Equipment>();

    public EquipmentBag() {
        super();
    }

    public EquipmentBag(EquipmentBag other) {
        for (Equipment e : other.toArray()) {
            set.add(e);
        }
    }

    public boolean add(Equipment newEntry) throws IncorrectBagContentsException {
        Equipment item = (set.size() != 0) ? set.toArray(new Equipment[0])[0] : null;
        if ((item instanceof ResearchEquipment && newEntry instanceof HazardEquipment) || (item instanceof HazardEquipment && newEntry instanceof ResearchEquipment)){
            throw new IncorrectBagContentsException("Researchers cannot carry different types of equipment in their bags");
        }
        if (set.size() < 3) {
            return set.add(newEntry);
        } else {
            return false;
        }
    }

	public boolean isEmpty() {
		return set.isEmpty();
	}

	public boolean isFull() {
		return set.size()==3;
	}

    public boolean hasResearchEquipment(){
        return (((set.size() != 0) ? set.toArray(new Equipment[0])[0] : null) instanceof ResearchEquipment);
    }

	public boolean remove(Equipment anEntry) {
        return set.remove(anEntry);
    }

	public boolean contains(Equipment anEntry) {
		return set.contains(anEntry);
	}

    public Equipment[] toArray() {
        return set.toArray(new Equipment[0]);
    }
}
