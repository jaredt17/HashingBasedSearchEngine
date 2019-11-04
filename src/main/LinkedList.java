package main;
import java.util.NoSuchElementException;

/**
 * Linked List class used for all other classes besides main. Allows for insertion, appending, deletion at front and back, and peeking for a last element.
 * @author jared
 *
 * @param <E> E for element.
 */
public class LinkedList<E> {
	
	//create node for head and tail
	public Node<E> head;
	public Node<E> tail;
	private int listSize;

	/**
	 * Constructor for linkedList
	 */
	LinkedList()
	{
		listSize = 0;
	}
	
	/**
	 * Returns size of the linkedList
	 * @return listSize or the size of the linkedList
	 */
	public int size()
	{
		return listSize;
	}
	
	/**
	 * Checks if the linkedList is empty
	 * @return True or false whether or not the linkedlist is empty.
	 */
	public boolean isEmpty()
	{
		return listSize == 0;
	}
	
	/**
	 * Adds an element to the front of the Linked List. Not sure if necessary for this program.
	 * @param element element to be added.
	 */
	public void addFront(E element)
	{
		Node<E> temp = new Node<>(element, head, null);
		if(head!=null) //if head is not null create a node previous to head
		{
			head.setPrev(temp);
		}
		head = temp;
		if(tail!=null) //if tail is not null set tail to temp
		{
			tail = temp;
		}
		listSize++;
	}
	
	/**
	 * Append an element to the end of the LinkedList.
	 * @param element Element to be appended.
	 */
	void append(E element)
	{
		Node<E> temp = new Node<>(element, null, tail);
		if(tail != null) //if tail is not null set the next element from tail to temp
		{
			tail.setNext(temp);
		}
		tail = temp;
		if(head == null)//if head is null make head the temp node
		{
			head = temp;
		}
		listSize++;
	}
	
	/*public void insertAtIndex(E element, int index)
	{
		Node<E> x = head;
		int counter = 0;
		while(x.getNext() != null)
		{
			if(index == counter)
			{
				Node<E> temp = x.getNext().getNext();
				x.setNext(new Node<E>(element));
				
				x.getNext().setNext(temp);
				//x.getNext().getNext().ge = x;
			}
			x = x.getNext();
			counter++;
		}
	}*/
	
	/**
	 * Removes first element of linkedList.
	 * @return Element that was removed.
	 */
	E removeFirstElement()
	{
		if(listSize == 0)//if there are no elements
		{
			throw new NoSuchElementException();
		}
		Node<E> temp = head;
		head = head.getNext(); //progress head
		listSize--;
		return temp.data;
	}
	
	/**
	 * Removes last element of linkedList.
	 * @return Element that was removed.
	 */
	E removeLastElement()
	{
		if(listSize == 0) //if there are no elements to remove
		{
			throw new NoSuchElementException();
		}
		Node<E> temp = tail;
		tail = tail.getPrev(); //regress tail by one
		listSize--;
		return temp.data;
	}
	
	/**
	 * Peeks to the last element of the linked list.
	 * @return Returns data stored in the last element
	 */
	E peekLastElement()
	{
		Node<E> temp = tail;
		return temp.data;
	}
	
}