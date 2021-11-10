
public class CourseDBElement implements Comparable{
	String courseID;
	int CRN;
	int credits;
	String roomNum;
	String instructor;
	public CourseDBElement() {
		
	}
	public CourseDBElement(String courseID, int CRN, int credits, String roomNum, String instructor) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	/**
	 * Sets the course registration number
	 * @param CRN The course registration number to be set to
	 */
	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	
	/**
	 * Checks whether this CourseDBElement is equal to a
	 * target object, not used in this project
	 * @param o An Object to compare with the CDE
	 * @return int 0 if the two objects are the same, and -1 if they are different
	 */
	@Override
	public int compareTo(Object o) {
		if(o.equals(this)) {
			return 0;
		}
		return -1;
	}
	
	/**
	 * Gets the course registration number
	 * @return int The course registration number being received
	 */
	public int getCRN() {
		return CRN;
	}
	
	/**
	 * Gets the hash code, in this case obtained easily by simply returning the
	 * CRN, since this project uses the CRN as a hash code
	 * @return int The hash code, also known as the CRN
	 */
	public int hashCode() {
		return CRN;
	}
	
	/**
	 * Returns the components of the CDE in String form, useful for some of the tests and for the main GUI
	 * @return String The String form of the CDE
	 */
	public String toString() {
		return "\nCourse:" + courseID + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructor + " Room:" + roomNum;
	}
}
