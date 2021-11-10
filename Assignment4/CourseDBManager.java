

import java.io.*;
import java.util.*;

/**
 * Implementation of the CourseDBManagerInterface, can perform
 * various functions to be used by the programs GUI
 * @author Evan Song
 */
public class CourseDBManager implements CourseDBManagerInterface{
	CourseDBStructure CDBS = new CourseDBStructure(100);
	
	/**
	 * Adds a CourseDBElement to a CourseDBStructure given
	 * the specific components of the CourseDBElement
	 * @param id A String representing the Course's ID
	 * @param CRN An int representing the Course's course registration number
	 * @param credits An int representing the Course's number of credits
	 * @param roomNum A String representing the Course's room number/name
	 * @param instructor A String representing the Course's instructor name
	 */
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement CDBE = new CourseDBElement(id, crn, credits, roomNum, instructor);
		CDBS.add(CDBE);
	}
	
	/**
	 * Gets a CourseDBElement from a CourseDBStructure given
	 * the CourseDBElement's CRN
	 * @param crn An integer used to find the correct CourseDBElement
	 * @return CourseDBElement The target CDE, or null if it is not contained by the CDS
	 */
	public CourseDBElement get(int crn) {
		try {
			return CDBS.get(crn);
		} catch(IOException e) {
			return null;
		}
	}
	
	/**
	 * Reads a file of course components and converts them all into CourseDBElements, 
	 * before adding those CDEs into the CDS
	 * @param input A File containing course components in String form
	 * @throws FileNotFoundException
	 */
	public void readFile(File input) throws FileNotFoundException {
		Scanner scan = new Scanner(input);
		String course;
		String[] courseList = new String[5];
		CourseDBElement CDBE;
		while(scan.hasNextLine()) {
			course = scan.nextLine();
			courseList = course.split(" ");
			CDBE = new CourseDBElement(courseList[0], Integer.parseInt(courseList[1]), Integer.parseInt(courseList[2]), courseList[3], courseList[4]);
			CDBS.add(CDBE);
		}
	}

	/**
	 * Returns an ArrayList of Strings, with each string representing a CDE
	 * @return ArrayList<String> The list of CDEs in String form
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> result = new ArrayList<>();
		String course;
		for(StudentMadeLinkedList<CourseDBElement> LL : CDBS.hashTable) {
			if(LL != null) {
				for(CourseDBElement CDBE : LL.toArray()) {
					course = "\nCourse:" + CDBE.courseID + " CRN:" + CDBE.CRN + " Credits:" + CDBE.credits + " Instructor:" + CDBE.instructor + " Room:" + CDBE.roomNum;
					result.add(course);
				}
			}
		}
		return result;
	}

}