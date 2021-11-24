

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeTreeTest_STUDENT {
	MorseCodeTree testTree;
	ArrayList<String> correctResult;
	@Before
	public void setUp() throws Exception {
		testTree = new MorseCodeTree();
		correctResult = new ArrayList<String>();
		correctResult.add("h");
		correctResult.add("s");
		correctResult.add("v");
		correctResult.add("i");
		correctResult.add("f");
		correctResult.add("u");
		correctResult.add("e");
		correctResult.add("l");
		correctResult.add("r");
		correctResult.add("a");
		correctResult.add("p");
		correctResult.add("w");
		correctResult.add("j");
		correctResult.add("");
		correctResult.add("b");
		correctResult.add("d");
		correctResult.add("x");
		correctResult.add("n");
		correctResult.add("c");
		correctResult.add("k");
		correctResult.add("y");
		correctResult.add("t");
		correctResult.add("z");
		correctResult.add("g");
		correctResult.add("q");
		correctResult.add("m");
		correctResult.add("o");
	}

	@After
	public void tearDown() throws Exception {
		testTree = null;
		correctResult = null;
	}

	@Test
	public void testToArrayList()
	{
		//Tests both buildTree (And therefore insert and addToNode) and toArrayList
		assertEquals(correctResult, testTree.toArrayList());
	}
	
	@Test 
	public void testSetRoot() {
		//Tests both setRoot and getRoot
		assertEquals("", testTree.getRoot().getData());
		testTree.setRoot(new TreeNode("Test Purposes Only"));
		assertEquals("Test Purposes Only", testTree.getRoot().getData());
	}
	
	@Test
	public void testFetch() {
		//Tests fetch (And therefore fetchNode)
		assertEquals("k", testTree.fetch("-.-"));
		assertEquals("l", testTree.fetch(".-.."));
		assertEquals("e", testTree.fetch("."));
		assertEquals("c", testTree.fetch("-.-."));
	}
	
	@Test
	public void testDelete() {
		try {
			testTree.delete("Test");
			assertTrue("This method should have thrown an UnsupportedOperationException", false);
		} catch(UnsupportedOperationException e) {
			assertTrue("This method should have thrown an UnsupportedOperationException", true);
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			testTree.update();
			assertTrue("This method should have thrown an UnsupportedOperationException", false);
		} catch(UnsupportedOperationException e) {
			assertTrue("This method should have thrown an UnsupportedOperationException", true);
		}
	}
}