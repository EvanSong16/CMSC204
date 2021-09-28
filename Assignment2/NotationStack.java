import java.util.ArrayList;

/** Interface for a generic Stack data structure
 * 
 * @author Professor Kartchner
 *
 * @param <T> data type
 */
public class NotationStack<T> implements StackInterface<T> {
	final int DEFAULT_SIZE = 99;
	private ArrayList<T> data;
	private int maxSize;
	
	public NotationStack() {
		data = new ArrayList<T>();
		maxSize = DEFAULT_SIZE;
	}
	
	public NotationStack(int inputSize) {
		data = new ArrayList<T>();
		maxSize = inputSize;
	}
	
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		return (data.size() == 0);
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return (data.size() == maxSize);
	}
	

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException("You nincompoop! You've tried to pop from an empty stack!");
		}
		T stackTop = data.get(size()-1);
		data.remove(size()-1);
		return stackTop;
	}
	
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException("You oaf! You've tried to top from an empty stack!");
		}
		return data.get(size()-1);
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, throws an Exception if not
	 */
	public boolean push(T e) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException("You cretin! You've tried to push onto a full stack!");
		}
		data.add(e);
		return true;
	}
	
	
	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String result = "";
		for(T obj : data) {
			result += obj;
		}
		return result;
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	public String toString(String delimiter) {
		String result = "";
		for(T obj : data) {
			result += obj + delimiter;
		}
		if(!isEmpty()) {
			result = result.substring(0, result.length()-delimiter.length());
		}
		return result;
	}
	
	 /**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
	  * list reference within your Stack, you will be allowing direct access to the data of
	  * your Stack causing a possible security breech.
	  * @param list elements to be added to the Stack from bottom to top
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> safeList = new ArrayList<T>();
		for(T obj : list) {
			safeList.add(obj);
		}
		for(T obj : safeList) {
			push(obj);
		}
	}
 
}