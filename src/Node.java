/**
 * Representing a node in the {@link LinkedList} linked list. Each node contain
 * a generic data object.
 * 
 * @author Foothill College, Wendi Luo
 */
public class Node<T> {
	private T data;
	private Node<T> next;

	/**
	 * Create a {@link Node} instance, leaving data null.
	 */
	public Node() {
		this.data = null;
	}

	/**
	 * Create a {@link Node} instance.
	 * 
	 * @param data
	 *            the data to be stored
	 */
	public Node(T data) {
		this.data = data;
	}

	/**
	 * Create a {@link Node} instance.
	 * 
	 * @param data
	 *            the data object to be stored
	 * @param next
	 *            pointer to the next node
	 */
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Getter for the pointer to the next node.
	 * 
	 * @return pointer to the next node
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Setter for the pointer to the next node.
	 * 
	 * @param next
	 *            the node to be set as this node's next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * Getter for the stored data.
	 * 
	 * @return the stored data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Override of the original toString method.
	 * 
	 * @return the toString method of the stored data.
	 */
	@Override
	public String toString() {
		return data.toString();
	}

}
