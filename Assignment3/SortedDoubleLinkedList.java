import java.util.Comparator;
/**
 * A child class of the Basic Double Linked List, this list
 * does not support the addToFront or addToEnd methods, but has
 * a new method "add", which adds data into the list in order
 * 
 * @author Evan Song
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	Comparator<T> comp;
	public SortedDoubleLinkedList(Comparator<T> comp) {
		super();
		this.comp = comp;
	}
	/**
	 * An unsupported method that throws an exception
	 * @param data Data to be added into the list, doesn't really matter though,
	 * since the method will always throw an UnsupportedOperationException
	 */
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * An unsupported method that throws an exception
	 * @param data Data to be added into the list, doesn't really matter though,
	 * since the method will always throw an UnsupportedOperationException
	 */
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	/**
	 * This method cycles through the nodes and adds the data in when the inserted data is less than
	 * the data in the nodes. If the list is empty, it adds the data as both the head and the tail.
	 * The final result should be an alphabetical list
	 * @param data Data to be added into the list
	 */
	public void add(T data) {
		if(size == 0) {
			Node<T> single = new Node<T>(data);
			head = single;
			tail = single;
			size++;
		} else {
			Node<T> check = head;
			while(check != null) {
				if(comp.compare(data, check.data) < 0) {
					Node<T> newNode = new Node<T>(data, check.previous, check);
					if(newNode.previous == null) {
						head = newNode;
					} else {
						newNode.previous.next = newNode;
					}
					check.previous = newNode;
					size++;
					return;
				}
				check = check.next;
			}
			Node<T> endNode = new Node<T>(data, tail, null);
			tail.next = endNode;
			tail = endNode;
			size++;
		}
	}
	/**
	 * Returns a List Iterator at the start of the list
	 * @return ListIterator<T> a List Iterator initialized at the beginning of the list 
	 */
	public ListIterator<T> iterator() {
		return super.iterator();
	}
	/**
	 * This method cycles through every node on the list until it finds a match, then cuts
	 * that node out of the list. If it doesn't find a match, it doesn't change anything.
	 * @param targetData The data to be matched and then removed
	 * @param comparator The comparator used to check whether the data is a match
	 * @return BasicDoubleLinkedList<T> A reference to the list, either changed, unchanged, or empty
	 */
	public SortedDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(targetData, comparator);
	}
}
