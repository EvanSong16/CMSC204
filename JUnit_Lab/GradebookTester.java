
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * STUDENT tests for the methods of GradeBook
 * @author Evan Song
 */
public class GradebookTester {
	GradeBook book1;
	GradeBook book2;
	GradeBook emptybook;
	/**
	 * This method finishes instantiating the two GradeBooks,
	 * filling them with scores. Both books have a capacity of
	 * 5, but book1 contains 3 scores and book2 contains 4 scores.
	 * <p>
	 * An empty gradebook, which also has a capacity of 5, 
	 * is also instantiated to test empty cases for the methods
	 */
	@Before
	public void setUp() {
		book1 = new GradeBook(5);
		book2 = new GradeBook(5);
		
		book1.addScore(12.6);
		book1.addScore(29.7);
		book1.addScore(14.8);
		
		book2.addScore(33.6);
		book2.addScore(8.3);
		book2.addScore(9.5);
		book2.addScore(27.9);
		
		emptybook = new GradeBook(5);
	}
	/**
	 * This method erases both of the GradeBooks
	 * as well as the empty book
	 */
	@After
	public void tearDown() throws Exception {
		book1 = null;
		book2 = null;
		
		emptybook = null;
	}
	/**
	 * Test if the addScore method is working as intended 
	 */
	@Test
	public void testAddScore() {
		assertTrue(book1.toString().equals("12.6 29.7 14.8"));
		assertTrue(book2.toString().equals("33.6 8.3 9.5 27.9"));
		
		assertEquals(book1.getScoreSize(), 3);
		assertEquals(book2.getScoreSize(), 4);
		
		assertTrue(emptybook.toString().equals(""));
		assertEquals(emptybook.getScoreSize(), 0);
	}
	/**
	 * Test if the sum method is working as intended 
	 */
	@Test
	public void testSum() {
		assertEquals(book1.sum(), 57.1, .0001);
		assertEquals(book2.sum(), 79.3, .0001);
		
		assertEquals(emptybook.sum(), 0.0, .0001);
	}
	/**
	 * Test if the minimum method is working as intended 
	 */
	@Test
	public void testMinimum() {
		assertEquals(book1.minimum(), 12.6, .0001);
		assertEquals(book2.minimum(), 8.3, .0001);
		
		assertEquals(emptybook.minimum(), 0.0, .0001);
	}
	/**
	 * Test if the finalScore method is working as intended 
	 */
	@Test
	public void testFinalScore() {
		assertEquals(book1.finalScore(), 44.5, .0001);
		assertEquals(book2.finalScore(), 71.0, .0001);
		
		assertEquals(emptybook.finalScore(), 0.0, .0001);
	}
}