import java.util.ArrayList;

/**
 * A custom implementation of a Linked List with 
 * very few functions, only implements the specific functions needed 
 * for the GUI and the project as a whole
 * @author Evan Song
 *
 * @param <T> Generic type, but should always be of type CourseDBElement in practice
 */
public class StudentMadeLinkedList<T> {
	Node<T> start;

	/**
	 * A nested class defining a node, holding data and a reference to the next node in the linked list. 
	 * If there is no next node, the next variable will be null; The list only keeps track of the first
	 * node, to get to the last node, you must traverse the entire Linked List
	 * @author Evan Song
	 *
	 * @param <T> Generic that will always be type CourseDBElement for this project
	 */
	public class Node<T> {
		T data;
		Node<T> next;
		public Node(T data) {
			this.data = data;
		}
		
		/**
		 * Sets the next node if you have a Node you need to update
		 * @param n The new next Node
		 */
		public void setNext(Node<T> n) {
			this.next = n;
		}
	}
	
	/**
	 * Adds a node to the end, by iterating to the end of the list and then adding 
	 * a new node to the end, updating the old last nodes next field in the process
	 * @param addedData The data to be contained in the data field, always a CDE in the case of the project
	 */
	public void addLast(T addedData) {
		Node<T> iter = start;
		if(start == null) {
			start = new Node<T>(addedData);
		} else {
			while(iter.next != null) {
				iter = iter.next;
			}
			iter.next = new Node<T>(addedData);
		}
	}

	/**
	 * Iterates through all of the nodes and checks whether their data fields contain 
	 * a match for a target data.
	 * @param target The target data to check for
	 * @return boolean True if the list contains that data, but False if it does not
	 */
	public boolean contains(T target) {
		Node<T> iter = start;
		if(start == null) {
			return false;
		} else {
			while(iter != null) {
				if(iter.data.equals(target)) {
					return true;
				}
				iter = iter.next;
			}
		}
		return false;
	}

	/**
	 * Converts the Linked List into an Array List in order to make for
	 * each loops work without having to implement Iterable
	 * @return ArrayList<T> An Array List representing the Linked List
	 */
	public ArrayList<T> toArray() {
		ArrayList<T> result = new ArrayList<>();
		Node<T> iter = start;
		if(start == null) {
			return result;
		} else {
			while(iter != null) {
				result.add(iter.data);
				iter = iter.next;
			}
		}
		return result;
	}
	
	/**
	 * Returns the data contained by the node at a specific index in the list
	 * @param i The index of the node containing the target data
	 * @return T The data contained by the node at a target index
	 */
	public T get(int i) {
		Node<T> iter = start;
		for(int j = 0; j < i; j++) {
			iter = iter.next;
		}
		return iter.data;
	}
}
