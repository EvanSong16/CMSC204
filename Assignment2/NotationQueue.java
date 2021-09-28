import java.util.ArrayList;

/** Interface for a Queue data structure
 * 
 * @author Professor Kartchner
 *
 * @param <T> data type
 */
public class NotationQueue<T> implements QueueInterface<T> {
	final int DEFAULT_SIZE = 99;
	private ArrayList<T> data;
	private int maxSize;
	
	public NotationQueue() {
		data = new ArrayList<T>();
		maxSize = DEFAULT_SIZE;
	}
	
	public NotationQueue(int inputSize) {
		data = new ArrayList<T>();
		maxSize = inputSize;
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		return (data.size() == 0);
	}

	/**
	 * Determines if Queue is full
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		return (data.size() == maxSize);
	}
	
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException("You fool! You've tried to dequeue from an empty queue!");
		}
		T queueFront = data.get(0);
		data.remove(0);
		return queueFront;
	}

	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, throws an Exception if not
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException("You buffoon! You've tried to enqueue into a full queue!");
		}
		data.add(e);
		return true;
	}
	
	
	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String result = "";
		for(T obj : data) {
			result += obj;
		}
		return result;
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
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
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * Makes a separate ArrayList to avoid data breaches
	  * @param list elements to be added to the Queue
	  */
	public void fill(ArrayList<T> list) {
		ArrayList<T> safeList = new ArrayList<T>();
		for(T obj : list) {
			safeList.add(obj);
		}
		for(T obj : safeList) {
			enqueue(obj);
		}
	}
	
 

}