package main;

/**
 * Node class used as a helper to linkedList to store Node data like pointers to previous and next nodes.
 * @author jared
 *
 * @param <E> Element of any type can be used to make a Node.
 */
class Node<E> {
	E data;
	private Node<E> next;
	private Node<E> prev;
	
	/**
	 * Constructor for a Node
	 * @param e The element to be added.
	 * @param n The next node that is linked to current Node.
	 * @param p The previous node that is linked to current Node.
	 */
	Node(E e, Node<E> n, Node<E> p)
	{
		this.data = e;
		this.setNext(n);
		this.setPrev(p);
	}
	
	Node(E e)
	{
		this.data = e;
		this.setNext(null);
		this.setPrev(null);
	}
	
	/**
	 * Getter for previous node
	 * @return Previous node.
	 */
	Node<E> getPrev() {
		return prev;
	}

	/**
	 * Setter for previous Node.
	 * @param prev Node previous to the current Node.
	 */
	void setPrev(Node<E> prev) {
		this.prev = prev;
	}
	
	/**
	 * Getter for Next Node.
	 * @return Node pointed to Next.
	 */
	Node<E> getNext() {
		return next;
	}

	/**
	 * Setter for Next Node.
	 * @param next The next node that current Node will point to.
	 */
	void setNext(Node<E> next) {
		this.next = next;
	}
	
}