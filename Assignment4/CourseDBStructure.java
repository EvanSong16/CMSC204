import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{
	StudentMadeLinkedList<CourseDBElement>[] hashTable;
	int capacity;
	final int DEFAULT_CAPACITY = 8;
	final int MAX_CAPACITY = 100;
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure() {
		capacity = DEFAULT_CAPACITY;
		hashTable = new StudentMadeLinkedList[capacity];
	}
	public CourseDBStructure(int capacity) {
		if (capacity < MAX_CAPACITY) {
			this.capacity = capacity;
		} else {
			this.capacity = MAX_CAPACITY;
		}
		hashTable = new StudentMadeLinkedList[capacity];
	}
	public CourseDBStructure(String testing, int capacity) {
		if (capacity < MAX_CAPACITY) {
			this.capacity = capacity;
		} else {
			this.capacity = MAX_CAPACITY;
		}
		hashTable = new StudentMadeLinkedList[capacity];
	}
	
	
	/** 
	* Use the hashcode of the CourseDatabaseElement to see if it is 
	* in the hashtable.
	* 
	* If the CourseDatabaseElement does not exist in the hashtable,
	* add it to the hashtable.
	* 
	* @param element the CDE to be added
	*/
	public void add(CourseDBElement element) {
		//Converting search key to hash
		int hashcode = element.CRN;
		//Compressing hash
		hashcode %= capacity;
		if(hashTable[hashcode] == null) {
			hashTable[hashcode] = new StudentMadeLinkedList<CourseDBElement>();
		}
		try {
			get(hashcode);
		} catch(IOException e) {
			hashTable[hashcode].addLast(element);
		}
	}
	
	/** 
	* Use the hashcode of the CourseDatabaseElement to see if it is 
	* in the hashtable.
	* 
	* If the CourseDatabaseElement is in the hashtable, return it
	* If not, throw an IOException
	* 
	* @param element the CDE to be added
	 * @throws IOException 
	*/
	public CourseDBElement get(CourseDBElement element) throws IOException {
		//Converting search key to hash
		int hashcode = element.CRN;
		//Compressing hash
		hashcode %= capacity;
		if (hashTable[hashcode].contains(element)) {
			return element;
		}
		throw new IOException();
	}
	
	

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	* @return int The number of indexes in the array
	*/
	public int getTableSize() {
		return capacity;
	}
	
	/**
	 * Returns a CourseDBElement given its registration number
	 * @param crn The course registration number of the CDE
	 * @return CourseDBElement, the target CDE, or null if the CDE is not contained
	 */
	public CourseDBElement get(int crn) throws IOException {
		//Converting search key to hash
		int hashcode = crn;
		//Compressing hash
		hashcode %= capacity;
		for(CourseDBElement cde : hashTable[hashcode].toArray()) {
			if(cde.CRN == crn) {
				return cde;
			}
		}
		throw new IOException();
	}
}
