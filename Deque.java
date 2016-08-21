
public interface Deque<E> {
	
	// Returns number of elements in the deque (0 if empty)
	public int size();
	
	// Returns true if empty, false otherwise
	public boolean isEmpty();
	
	// Returns the value of the first element, null if empty
	public E peekFirst();
	
	// Returns the value of the last element, null if empty
	public E peekLast();
	
	// Returns the value of the first element, throws exception if empty
	public E getFirst() throws IllegalStateException;
	
	// Returns the value of the last element, throws exception if empty
	public E getLast() throws IllegalStateException;
	
	// Adds the element to the front, throws exception if full
	public void addFirst(E element) throws IllegalStateException;
	
	// Adds the element to the end, throws exception if full
	public void addLast(E element) throws IllegalStateException;
	
	// Adds the element to the front, returns false if full, true otherwise
	public boolean offerFirst(E element);
	
	// Adds the element to the end, returns false if full, true otherwise
	public boolean offerLast(E element);
	
	// Removes the first element and returns its value, null if empty
	public E pollFirst();
	
	// Removes the last element and returns its value, null if empty
	public E pollLast();
	
	// Removes the first element and returns its value, throws exception if empty
	public E removeFirst() throws IllegalStateException;
	
	// Removes the last element and returns its value, throws exception if empty
	public E removeLast() throws IllegalStateException;
}
