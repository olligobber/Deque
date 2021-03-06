
public class ExpandingArrayDeque<E> extends ArrayDeque<E> {
	
	private boolean expanding;
	
	public ExpandingArrayDeque() {
		super(1024);
		expanding = true;
	}
	
	public ExpandingArrayDeque(int i) {
		super(i);
		expanding = false;
	}

	private void checkSize() {
		if (size == maxSize && expanding) {
			maxSize *= 2;
			@SuppressWarnings("unchecked")
			E[] newArray = (E[]) new Object[maxSize];
			for (int i=0; i< size; ++i) {
				newArray[i] = array[(header+i)%size];
			}
			array = newArray;
			header = 0;
		}
	}
	
	public void addFirst(E element) {
		checkSize();
		super.addFirst(element);
	}
	
	public void addLast(E element) {
		checkSize();
		super.addLast(element);
	}
	
	public boolean offerFirst(E element) {
		checkSize();
		return super.offerFirst(element);
	}
	
	public boolean offerLast(E element) {
		checkSize();
		return super.offerLast(element);
	}
}
