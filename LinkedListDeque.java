
public class LinkedListDeque<E> implements Deque<E> {
	
	private static class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> prev;
		
		public Node(E element) {
			this.element = element;
			this.next = null;
			this.prev = null;
		}
		
		public Node() {
			this(null);
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public Node<E> getPrev() {
			return prev;
		}
		
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		
		public E getElement() {
			return element;
		}
		
		/*
			public void setElement(E element) {
				this.element = element;
			}
		*/
	}
	
	private Node<E> header;
	private Node<E> trailer;
	private int size;
	private int maxSize;
	private boolean isCapped;
	
	public LinkedListDeque() {
		header = new Node<E>();
		trailer = new Node<E>();
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
		isCapped = false;
	}
	
	public LinkedListDeque(int size) {
		this();
		isCapped = true;
		maxSize = size;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public E peekFirst() {
		if (isEmpty()) return null;
		return (header.getNext().getElement());
	}
	
	public E peekLast() {
		if (isEmpty()) return null;
		return (trailer.getPrev().getElement());
	}
	
	public E getFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("Cannot retrieve element from empty deque");
		return (header.getNext().getElement());
	}
	
	public E getLast() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("Cannot retrieve element from empty deque");
		return (trailer.getPrev().getElement());
	}
	
	public void addFirst(E element) throws IllegalStateException {
		if (size == maxSize && isCapped) throw new IllegalStateException("Cannot add element to full deque");
		Node<E> newNode = new Node<E>(element);
		header.getNext().setPrev(newNode);
		newNode.setNext(header.getNext());
		header.setNext(newNode);
		newNode.setPrev(header);
		size++;
	}
	
	public void addLast(E element) throws IllegalStateException {
		if (size == maxSize && isCapped) throw new IllegalStateException("Cannot add element to full deque");
		Node<E> newNode = new Node<E>(element);
		trailer.getPrev().setNext(newNode);
		newNode.setPrev(trailer.getPrev());
		trailer.setPrev(newNode);
		newNode.setNext(trailer);
		size++;
	}
	
	public boolean offerFirst(E element) {
		if (size == maxSize && isCapped) return false;
		Node<E> newNode = new Node<E>(element);
		header.getNext().setPrev(newNode);
		newNode.setNext(header.getNext());
		header.setNext(newNode);
		newNode.setPrev(header);
		size++;
		return true;
	}
	
	public boolean offerLast(E element) {
		if (size == maxSize && isCapped) return false;
		Node<E> newNode = new Node<E>(element);
		trailer.getPrev().setNext(newNode);
		newNode.setPrev(trailer.getPrev());
		trailer.setPrev(newNode);
		newNode.setNext(trailer);
		size++;
		return true;
	}
	
	public E pollFirst() {
		if (isEmpty()) return null;
		E element = header.getNext().getElement();
		header.getNext().getNext().setPrev(header);
		header.setNext(header.getNext().getNext());
		size--;
		return element;
	}
	
	public E pollLast() {
		if (isEmpty()) return null;
		E element = trailer.getPrev().getElement();
		trailer.getPrev().getPrev().setNext(trailer);
		trailer.setPrev(trailer.getPrev().getPrev());
		size--;
		return element;
	}
	
	public E removeFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("Cannot remove element from empty deque");
		E element = header.getNext().getElement();
		header.getNext().getNext().setPrev(header);
		header.setNext(header.getNext().getNext());
		size--;
		return element;
	}
	
	public E removeLast() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("Cannot remove element from empty deque");
		E element = trailer.getPrev().getElement();
		trailer.getPrev().getPrev().setNext(trailer);
		trailer.setPrev(trailer.getPrev().getPrev());
		size--;
		return element;
	}
}
