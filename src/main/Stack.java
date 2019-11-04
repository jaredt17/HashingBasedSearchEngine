package main;

/**
 * This class will allow for the creation of stack objects using linkedLists
 * @author jared
 *
 * @param <E> E is a placeholder for element that will be used throughout the program
 */
public class Stack<E> {
	
	private LinkedList<E> list = new LinkedList<>();
	
	//we will use this as extra space for finding max in our stack
	//private LinkedList<E> maxList = new LinkedList<>();
	
	private int sizeOfStack = 0;

	/**
	 * Size method for returning size of the stack
	 * @return Size of the stack
	 */
	int size()
	{
		return sizeOfStack;
	}
	
	/**
	 * Pushes elements onto a stack by appending them to a linkedList. Also stores a max value in another linked list for use later.
	 * @param s Data to be added to the stack, can be of any type.
	 */
	void push(E s)
	{
		list.append(s); //use our linked list append function to add an element to the stack
		sizeOfStack++;
	}
	
	/**
	 * Pops data off of a stack by removal from the linked list from the last element. 
	 * @return The data that was popped off of the stack.
	 */
	E pop()
	{
		E temp;
		if(sizeOfStack == 0) //if there are no elements return null
		{
			return null;
		}
		else if(sizeOfStack == 1) //if there is one element remove from last elements
		{
			temp = list.removeLastElement();
			sizeOfStack--;
		}
		else //remove from last element
		{
			temp = list.removeLastElement();
			sizeOfStack--;
		}
		return temp;
	}

	E peek(){
		return list.tail.data;
	}
	
	/**
	 * Prints out the maximum value that is in the stack currently.
	 */
	/*
	public void getMax()
	{
		if(sizeOfStack == 0) //there are no elements
		{
			System.out.println("Max value is NULL as stack is empty");
		}
		else //there is a max value on the stack
		{
			
			System.out.println("Max value is " + maxList.peekLastElement());
		}
	}
	*/


	public void printStack(){
		Node<E> tail = list.tail;
		int i = 0;
		while(tail!=null){
			System.out.println(tail.data);
			tail = tail.getPrev();
			i++;
		}
	}


	
}
