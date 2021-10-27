import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class represents a Basic Double Linked List which contains several nodes,
 * each of which contains a reference to the node in front of it and the node
 * behind it. This class keeps track of the first and last nodes, as well as how
 * many nodes are contained by the list. It also has a variety of useful functions
 * for adding to, viewing from, and removing from the list. Lastly, the list has an
 * iterator function which can be used to iterate through the nodes in the list without
 * removing them.
 * 
 * @author Evan Song
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> {
	Node<T> head = null;
	Node<T> tail = null;
	int size = 0;
	/**
	 * An inner class that defines the Nodes in the list; Nodes are
	 * simple objects that can store data and references to the nodes
	 * in front and behind. They can be initialized empty, with only data, or
	 * with both data and references to previous and next nodes
	 * 
	 * @author Evan Song
	 *
	 * @param <T>
	 */
	class Node<T> {
		Node previous;
		Node next;
		T data;
		public Node() {
			previous = null;
			next = null;
			data = null;
		}
		public Node(T dataInput) {
			data = dataInput;
		}
		public Node(T dataInput, Node<T> prevInput, Node<T> nextInput) {
			data = dataInput;
			previous = prevInput;
			next = nextInput;
		}
	}
	/**
	 * An inner class that defines the Iterator in the list; Iterators are
	 * simple objects that can iterate through the nodes in a list.
	 * They can be initialized empty, or with a node to start at
	 * 
	 * @author Evan Song
	 *
	 * @param <T>
	 */
	class ListIterator<T> implements java.util.ListIterator{
		Node<T> prev;
		Node<T> next;
		public ListIterator() {
			prev = null;
			next = null;
		}
		public ListIterator(Node<T> nodeInput) {
			prev = null;
			next = nodeInput;
		}
		/**
		 * Simple check for a following node
		 * @return boolean True if there is a node following the iterator, False if not
		 */
		public boolean hasNext() {
			if(next != null) {
				return true;
			}
			return false;
		}
		/**
		 * Returns the data in the upcoming node, or throws an error if at the end of the list
		 * @return T the data contained by the next node
		 */
		public T next() throws NoSuchElementException {
			if(!hasNext()) {
				throw new NoSuchElementException("Sorry, you've reached the end of the list. You can no longer go forward");
			}
			prev = next;
			next = next.next;
			return prev.data;
		}
		/**
		 * Simple check for a previous node
		 * @return boolean True if there is a node before the iterator, False if not
		 */
		public boolean hasPrevious() {
			if(prev != null) {
				return true;
			}
			return false;
		}
		/**
		 * Returns the data in the previous node, or throws an error if at the beginning of the list
		 * @return T the data contained by the previous node
		 */
		public T previous() throws NoSuchElementException {
			if(!hasPrevious()) {
				throw new NoSuchElementException("Sorry, you're at the beginning of the list. You can not move backward");			}
			next = prev;
			prev = prev.previous;
			return next.data;
		}
		/**
		 * Unimplemented method, throws an UnsupportedOperationException
		 */
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Sorry, this method has not yet been implemented");
		}
		/**
		 * Unimplemented method, throws an UnsupportedOperationException
		 */
		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Sorry, this method has not yet been implemented");
		}
		/**
		 * Unimplemented method, throws an UnsupportedOperationException
		 */
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Sorry, this method has not yet been implemented");
		}
		/**
		 * Unimplemented method, throws an UnsupportedOperationException
		 */
		@Override
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Sorry, this method has not yet been implemented");
		}
		/**
		 * Unimplemented method, throws an UnsupportedOperationException
		 */
		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Sorry, this method has not yet been implemented");
		}
	}
	/**
	 * This method adds a data value to the end of a list
	 * @param data Data that will be added to the list
	 */
	public void addToEnd(T data) {
		if(size == 0) {
			Node<T> single = new Node<T>(data);
			head = single;
			tail = single;
		} else {
			tail = new Node<T>(data, tail, null);
			tail.previous.next = tail;
		}
		size++;
	}
	/**
	 * This method adds a data value to the front of a list
	 * @param data Data that will be added to the list
	 */
	public void addToFront(T data) {
		if(size == 0) {
			Node<T> single = new Node<T>(data);
			head = single;
			tail = single;
		} else {
			head = new Node<T>(data, null, head);
			head.next.previous = head;
		}
		size++;
	}
	/**
	 * Returns the first data point in the list
	 * @return T the data held by the first node in the list
	 */
	public T getFirst() {
		return head.data;
	}
	/**
	 * Returns the last data point in the list
	 * @return T the data held by the last node in the list
	 */
	public T getLast() {
		return tail.data;
	}
	/**
	 * Returns the number of nodes contained by the list
	 * @return int the size of the list
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Returns a List Iterator at the start of the list
	 * @return ListIterator<T> a List Iterator initialized at the beginning of the list 
	 */
	public ListIterator<T> iterator() {
		if(size == 0) {
			return new ListIterator<T>();
		}
		return new ListIterator<>(head);
	}
	/**
	 * This method cycles through every node on the list until it finds a match, then cuts
	 * that node out of the list. If it doesn't find a match, it doesn't change anything.
	 * @param targetData The data to be matched and then removed
	 * @param comparator The comparator used to check whether the data is a match
	 * @return BasicDoubleLinkedList<T> A reference to the list, either changed, unchanged, or empty
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		Node<T> check = head;
		while(check != null) {
			if(comparator.compare(check.data, targetData) == 0) {
				size--;
				if(check.previous != null) {
					check.previous.next = check.next;
				}
				if(check.next != null) {
					check.next.previous = check.previous;
				}
				if(head.equals(check)) {
					head = check.next;
				}
				if(tail.equals(check)) {
					tail = check.previous;
				}
				if(size == 0) {
					return null;
				}
				return this;
			}
			check = check.next;
		}
		return this;
	}
	/**
	 * Deletes and returns the first element of the list
	 * @return T the first element in the list
	 */
	public T retrieveFirstElement() {
		if(size == 0 || head == null) {
			return null;
		}
		T result;
		result = head.data;
		head = head.next;
		size--;
		return result;
	}
	/**
	 * Deletes and returns the last element of the list
	 * @return T the last element in the list
	 */
	public T retrieveLastElement() {
		if(size == 0 || tail == null) {
			return null;
		}
		T result;
		result = tail.data;
		tail = tail.previous;
		size--;
		return result;
	}
	/**
	 * Cycles through each node and adds the data to an Array List, then returns the Array List
	 * @return ArrayList<T> An array list filled with the data in the list
	 */
	public java.util.ArrayList<T> toArrayList() {
		Node<T> check = head;
		ArrayList<T> result = new ArrayList<T>();
		while(check != null && check.previous != tail) {
			result.add(check.data);
			check = check.next;
		}
		return result;
	}
}