package Bag;

import Equipment.*;
import Exceptions.*;

public class EquipmentBag<T extends Equipment>{

    Bag<T> bag = new Bag<T>();

    public EquipmentBag() {
        super();
    }

    public boolean add(T newEntry) throws IncorrectBagContentsException {
        T item = bag.peek();
        if ((item instanceof ResearchEquipment && newEntry instanceof HazardEquipment) || (item instanceof HazardEquipment && newEntry instanceof ResearchEquipment)){
            throw new IncorrectBagContentsException("Researchers cannot carry different types of equipment in their bags");
        }
        if (bag.getCurrentSize() < 3) {
            return bag.add(newEntry);
        } else {
            return false;
        }
    }

	public boolean isEmpty() {
		return bag.isEmpty();
	}

	public boolean isFull() {
		return bag.getCurrentSize()==3;
	}

    public boolean hasResearchEquipment(){
        return (bag.peek() instanceof ResearchEquipment);
    }

	public boolean remove(T anEntry) {
        return bag.remove(anEntry);
    }

	public boolean contains(T anEntry) {
		return bag.contains(anEntry);
	}
}
