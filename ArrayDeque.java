
public class ArrayDeque<E> implements Deque<E> {
	
	protected E[] array;
	protected int maxSize;
	protected int header;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public ArrayDeque(int size) {
		header = 0;
		this.size = 0;
		maxSize = size;
		array = (E[]) new Object[size];
	}
	
	public ArrayDeque() {
		this(1024);
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return (size==0);
	}
	
	public E peekFirst() {
		if (isEmpty()) return null;
		return array[header];
	}
	
	public E peekLast() {
		if (isEmpty()) return null;
		return array[(header+size-1)%maxSize];
	}
	
	public E getFirst() throws IllegalStateException{
		if (isEmpty()) throw new IllegalStateException("Cannot retrieve element from empty deque");
		return array[header];
	}
	
	public E getLast() throws IllegalStateException{
		if (isEmpty()) throw new IllegalStateException("Cannot retrieve element from empty deque");
		return array[(header+size-1)%maxSize];
	}
	
	public void addFirst(E element) throws IllegalStateException {
		if (size==maxSize) throw new IllegalStateException("Cannot add element to full deque");
		header = (header-1+maxSize)%maxSize;
		size++;
		array[header] = element;
	}
	
	public void addLast(E element) throws IllegalStateException {
		if (size==maxSize) throw new IllegalStateException("Cannot add element to full deque");
		array[(header+size)%maxSize] = element;
		size++;
	}
	
	public boolean offerFirst(E element) {
		if (size==maxSize) return false;
		header = (header-1+maxSize)%maxSize;
		size++;
		array[header] = element;
		return true;
	}
	
	public boolean offerLast(E element) {
		if (size==maxSize) return false;
		array[(header+size)%maxSize] = element;
		size++;
		return true;
	}
	
	public E pollFirst() {
		if (isEmpty()) return null;
		E out = array[header];
		array[header] = null;
		header = (header+1)%maxSize;
		size--;
		return out;
	}
	
	public E pollLast() {
		if (isEmpty()) return null;
		size--;
		E out = array[(header+size)%maxSize];
		array[(header+size)%maxSize] = null;
		return out;
	}
	
	public E removeFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("Cannot remove element from empty deque");
		E out = array[header];
		array[header] = null;
		header = (header+1)%maxSize;
		size--;
		return out;
	}
	
	public E removeLast() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("Cannot remove element from empty deque");
		size--;
		E out = array[(header+size)%maxSize];
		array[(header+size)%maxSize] = null;
		return out;
	}
}
