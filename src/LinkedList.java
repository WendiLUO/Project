import java.util.Iterator;

/**
 * Generic singly linked list of {@link Node}.
 * 
 * @author Foothill College, Wendi Luo
 */
public class LinkedList<T> implements Iterable<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;

	/**
	 * Create a {@link LinkedList} instance.
	 */
	public LinkedList() {
		// Initialize the head with a dummy CountryNode.
		this.head = new Node<T>();
		this.tail = this.head;
		this.size = 0;
	}

	/**
	 * Getter for the number of elements stored.
	 * 
	 * @return the number of elements stored.
	 */
	public int size() {
		return size;
	}

	/**
	 * Store the data object as a node, and add it to the linked list.
	 * 
	 * @param data
	 *            country to be added to the linked list
	 */
	public void add(T data) {
		Node<T> node = new Node<T>(data);
		tail.setNext(node);
		tail = tail.getNext();
		++size;
	}

	/**
	 * Store the data object as a node, and add it at the specified index.
	 * 
	 * @param data
	 *            country to be added to the linked list
	 * @param targetIndex
	 *            the index to insert the specific country
	 */
	public void insertAtIndex(T data, int targetIndex) {
		if (targetIndex < 0 || targetIndex > size) {
			return;
		}

		int counter = 0;
		Node<T> currentNode = head.getNext();
		Node<T> pre = head;
		while (currentNode != null && counter < targetIndex) {
			currentNode = currentNode.getNext();
			pre = pre.getNext();
			++counter;
		}
		Node<T> newNode = new Node<T>(data);
		if (currentNode != null) {
			Node<T> originalNext = pre.getNext();
			pre.setNext(newNode);
			newNode.setNext(originalNext);
		} else {
			pre.setNext(newNode);
		}
		++size;
	}

	/**
	 * Get the {@link Country} elements stored in the specific index, if the
	 * provided index exists.
	 * 
	 * @param targetIndex
	 *            the target index of the {@link Country} to be retrieved
	 * @return the {@link Country} element at the specific index if this index
	 *         exists, otherwise return null
	 */
	public T getIndex(int targetIndex) {
		// Skipping the dummy head.
		Node<T> currentNode = head.getNext();
		int counter = 0;
		while (currentNode != null && counter < targetIndex) {
			currentNode = currentNode.getNext();
			++counter;
		}
		return (currentNode != null) ? currentNode.getData() : null;
	}

	/**
	 * Return the stored data object if the provided data exists in the list, or
	 * null otherwise.
	 * 
	 * @param target
	 *            the data object to be found
	 * @return the stored data object if the provided data exists in the list,
	 *         or null otherwise.
	 */
	public T contains(T target) {
		// Skipping the dummy head.
		Node<T> currentNode = head.getNext();
		while (currentNode != null && !currentNode.getData().equals(target))
			currentNode = currentNode.getNext();
		return (currentNode != null) ? currentNode.getData() : null;
	}

	/**
	 * Override of the original toString method.
	 * 
	 * @return a string representing the stored linked list in a print friendly
	 *         form
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('\n');
		Iterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			T current = iterator.next();
			sb.append(current.toString());
			sb.append('\n');
		}
		return sb.toString();
	}

	/**
	 * Getter for the {@link Iterator} for the linked list.
	 */
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	/**
	 * Iterator of the linked list.
	 */
	private class ListIterator implements Iterator<T> {

		private Node<T> current;

		/**
		 * Construct a {@link ListIterator} object, current points the dummy
		 * head.
		 */
		private ListIterator() {
			current = head;
		}

		/**
		 * Whether the next object in the linked list is not null.
		 * 
		 * @return Whether the next object in the linked list is not null
		 */
		@Override
		public boolean hasNext() {
			return current != null && current.getNext() != null;
		}

		/**
		 * Getter of the next object in the list if exists.
		 * 
		 * @return the next object in the list if exists, null otherwise.
		 */
		@Override
		public T next() {
			if (hasNext()) {
				current = current.getNext();
				return current.getData();
			}
			return null;
		}
	}

}
