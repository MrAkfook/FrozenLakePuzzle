package Bag;

public class Bag<T> implements IBag<T>{

	private int Default_Capacity = 10;
	private T[] bag;
	private int itemCount;
	
	@SuppressWarnings("unchecked")
	public Bag() {
		this.bag = (T[]) new Object[Default_Capacity];
		this.itemCount = 0;
	}
	
	
	@Override
	public boolean add(T newEntry) {
		if(this.isFull()) {
			this.double_array();
		}
		this.bag[this.itemCount] = newEntry;
		this.itemCount++;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return this.itemCount == 0;
	}

	@Override
	public boolean isFull() {
		return this.itemCount == bag.length;
	}

	@Override
	public T removeByIndex(int index) {
		T result = bag[index];
		this.bag[index] = this.bag[itemCount];
		this.bag[itemCount] = null;
		this.itemCount = this.itemCount - 1;
		
		
		return result;
	}

	@Override
	public boolean remove(T anEntry) {
		int x = getIndexOf(anEntry);
		if(x != -1) {
			removeByIndex(x);
			return true;
		}
		return false;
	}

	public T peek(){
		if(!isEmpty()) {
			T x = this.bag[itemCount];
			return x;
		}
		return null;
	}

	@Override
	public T remove() {
		if(!isEmpty()) {
			T x = removeByIndex(this.itemCount);
			return x;
		}
		return null;
	}

	@Override
	public int getFrequencyOf(T anEntry) {
		int x = 0;
		for(int a=0; a < this.itemCount ; a++) {
			if(anEntry == this.bag[a]) {
				x++;
			}
		}
		return x;
	}

	@Override
	public int getIndexOf(T anEntry) {
		int index = -1;
		for(int i = 0;i<this.itemCount;i++) {
			if(this.bag[i].equals(anEntry)) {
				index = i;
			}
		}
		return index;
	}
	
	//anEntry.toString() == this.bag[i].toString() && 
	@Override
	public boolean contains(T anEntry) {
		return getIndexOf(anEntry) != -1;
	}

	@Override
	public void clear() {
		for(int x = 0;x < this.itemCount;x++) {
			this.bag[x] = null;
		}
		this.itemCount = 0;
	}

	@Override
	public void displayItems() {
		for(int i = 0;i < this.itemCount;i++) {
			System.out.print(bag[i] + " ");
		}
		System.out.println();
	}

	@Override
	public int getCurrentSize() {
		return this.itemCount;
	}

	@Override
	public T[] toArray() {
	    @SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.bag.length];
	    
	    for (int i = 0; i < this.bag.length; i++) {
	        if (this.bag[i] == null) {
	            array[i] = null;
	        } else {
	            array[i] = this.bag[i];
	        }
	    }
	    
	    return array;
	}
	
	private void double_array() {
		@SuppressWarnings("unchecked")
	    T[] new_bag = (T[]) new Object[this.bag.length * 2];
	    for (int i = 0; i < this.bag.length; i++) {
	        new_bag[i] = this.bag[i];
	    }
	    this.bag = new_bag;
	}


}

