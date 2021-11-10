

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 * @author Evan Song
 *
 */
public class CourseDBManager_STUDENT_TEST {
	CourseDBManager CDM;
	CourseDBElement CDE;

	/**
	 * Create an instance of CourseDBManager and CourseDBElement
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		CDM = new CourseDBManager();
		CDE = new CourseDBElement("CMSC204", 22124, 4, "Distance Learning", "Prof. Farnazi");
	}

	/**
	 * Set CDM and CDE reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		CDM = null;
		CDE = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			CDM.add("CMSC204", 22124, 4, "Distance Learning", "Prof. Farnazi");
			assertEquals(CDM.get(22124).courseID, CDE.courseID);
			assertEquals(CDM.get(22124).CRN, CDE.CRN);
			assertEquals(CDM.get(22124).credits, CDE.credits);
			assertEquals(CDM.get(22124).roomNum, CDE.roomNum);
			assertEquals(CDM.get(22124).instructor, CDE.instructor);
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		CDM.add("CMSC101",22121,4,"Computer Lab","Ada Lovelace");
		CDM.add("CMSC204", 22124, 4, "Distance Learning", "Prof. Farnazi");
		ArrayList<String> list = CDM.showAll();
		
		assertEquals(list.get(0),"\nCourse:CMSC101 CRN:22121 Credits:4 Instructor:Ada Lovelace Room:Computer Lab");
		assertEquals(list.get(1),"\nCourse:CMSC204 CRN:22124 Credits:4 Instructor:Prof. Farnazi Room:Distance Learning");
			}
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("studentTest.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC101 22121 4 Computer-Lab Ada-Lovelace");
			inFile.print("CMSC204 22124 4 Distance-Learning Prof.Farnazi");
			inFile.close();
			CDM.readFile(inputFile);
			ArrayList<String> list = CDM.showAll();
			
			assertEquals(list.get(0),"\nCourse:CMSC101 CRN:22121 Credits:4 Instructor:Ada-Lovelace Room:Computer-Lab");
			assertEquals(list.get(1),"\nCourse:CMSC204 CRN:22124 Credits:4 Instructor:Prof.Farnazi Room:Distance-Learning");
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}