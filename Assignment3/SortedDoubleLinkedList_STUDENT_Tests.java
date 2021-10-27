//package _solution;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedList_STUDENT_Tests {
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	DoubleComparator dComp;
	
	@Before
	public void setUp() throws Exception {	
		dComp = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(dComp);
	}

	@After
	public void tearDown() throws Exception {
		dComp = null;
		sortedLinkedDouble = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedDouble.addToEnd(43.43);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedDouble.addToEnd(100.100);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(1.1);
		sortedLinkedDouble.add(5.5);
		sortedLinkedDouble.add(4.4);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1.1, iterator.next(), 0.1);
		assertEquals(2.2, iterator.next(), 0.1);
		assertEquals(4.4, iterator.next(), 0.1);
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulPrevious() {
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(1.1);
		sortedLinkedDouble.add(5.5);
		sortedLinkedDouble.add(4.4);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1.1, iterator.next(), 0.1);
		assertEquals(2.2, iterator.next(), 0.1);
		assertEquals(4.4, iterator.next(), 0.1);
		assertEquals(5.5, iterator.next(), 0.1);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(5.5, iterator.previous(), 0.1);
		assertEquals(4.4, iterator.previous(), 0.1);
		assertEquals(2.2, iterator.previous(), 0.1);
		assertEquals(true, iterator.hasPrevious());
		assertEquals(1.1, iterator.previous(), 0.1);
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(1.1);
		sortedLinkedDouble.add(5.5);
		sortedLinkedDouble.add(4.4);
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(1.1, iterator.next(), 0.1);
		assertEquals(2.2, iterator.next(), 0.1);
		assertEquals(4.4, iterator.next(), 0.1);
		assertEquals(true, iterator.hasNext());
		assertEquals(5.5, iterator.next(), 0.1);
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddCar() {
		sortedLinkedDouble.add(9.9);
		sortedLinkedDouble.add(4.4);
		sortedLinkedDouble.add(6.6);
		assertEquals(4.4, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(9.9, sortedLinkedDouble.getLast(), 0.1);
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(12.12);
		assertEquals(2.2, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(12.12, sortedLinkedDouble.getLast(), 0.01);
		//removal
		assertEquals(12.12, sortedLinkedDouble.retrieveLastElement(), 0.01);
		assertEquals(9.9, sortedLinkedDouble.getLast(), 0.1);
	}

	@Test
	public void testRemoveFirstCar() {
		sortedLinkedDouble.add(9.9);
		sortedLinkedDouble.add(4.4);
		sortedLinkedDouble.add(6.6);
		assertEquals(4.4, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(9.9, sortedLinkedDouble.getLast(), 0.1);
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(12.12);
		assertEquals(2.2, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(12.12, sortedLinkedDouble.getLast(), 0.01);
		//removal
		assertEquals(2.2, sortedLinkedDouble.retrieveFirstElement(), 0.01);
		assertEquals(4.4, sortedLinkedDouble.getFirst(), 0.1);
	}
	
	@Test
	public void testRemoveEndCar() {
		sortedLinkedDouble.add(9.9);
		sortedLinkedDouble.add(4.4);
		sortedLinkedDouble.add(6.6);
		assertEquals(4.4, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(9.9, sortedLinkedDouble.getLast(), 0.1);
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(12.12);
		assertEquals(2.2, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(12.12, sortedLinkedDouble.getLast(), 0.01);
		//removal
		assertEquals(12.12, sortedLinkedDouble.retrieveLastElement(), 0.01);
		assertEquals(9.9, sortedLinkedDouble.getLast(), 0.1);
	}

	@Test
	public void testRemoveMiddleCar() {
		sortedLinkedDouble.add(9.9);
		sortedLinkedDouble.add(4.4);
		sortedLinkedDouble.add(6.6);
		assertEquals(4.4, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(9.9, sortedLinkedDouble.getLast(), 0.1);
		sortedLinkedDouble.add(2.2);
		sortedLinkedDouble.add(12.12);
		assertEquals(2.2, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(12.12, sortedLinkedDouble.getLast(), 0.01);
		//removal
		assertEquals(5, sortedLinkedDouble.getSize());
		assertEquals(2.2, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(12.12, sortedLinkedDouble.getLast(), 0.01);
		sortedLinkedDouble.remove(6.6, dComp);
		assertEquals(2.2, sortedLinkedDouble.getFirst(), 0.1);
		assertEquals(12.12, sortedLinkedDouble.getLast(), 0.01);
		assertEquals(4, sortedLinkedDouble.getSize());
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
}