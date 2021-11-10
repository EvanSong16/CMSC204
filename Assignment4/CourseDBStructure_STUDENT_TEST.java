

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * @author Evan Song
 */
public class CourseDBStructure_STUDENT_TEST {
	CourseDBStructure CDS, tester;
	CourseDBElement CDE;

	@Before
	public void setUp() throws Exception {
		CDS = new CourseDBStructure(32);
		tester = new CourseDBStructure("Just trying this one out", 16);
		CDE = new CourseDBElement("CMSC204", 22124, 4, "Distance Learning", "Prof. Farnazi");
	}

	@After
	public void tearDown() throws Exception {
		CDS = null;
		tester = null;
		CDE = null;
	}
	
	/**
	 * Test the tableSize for CourseDBStructures constructed
	 * with both constructors
	 */
	@Test
	public void testGetTableSize()
	{
		assertEquals(32, CDS.getTableSize());
		assertEquals(16, tester.getTableSize());		
	}
	
	/**
	 * Test the hashTable for CourseDBStructures constructed
	 * with both constructors
	 */
	@Test
	public void testHashTable()
	{
		assertEquals(32, CDS.hashTable.length);
		assertEquals(16, tester.hashTable.length);
		CDS.add(CDE);
		StudentMadeLinkedList<CourseDBElement> CDSBucket = CDS.hashTable[CDE.hashCode()%CDS.getTableSize()];
		assertEquals(CDE, CDSBucket.get(0));
		tester.add(CDE);
		StudentMadeLinkedList<CourseDBElement> testerBucket = CDS.hashTable[CDE.hashCode()%CDS.getTableSize()];
		assertEquals(CDE, testerBucket.get(0));
	}
}