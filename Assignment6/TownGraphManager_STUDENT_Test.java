


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	private String[] towns;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  towns = new String[11];
		  
		  for (int i = 1; i <= 10; i++) {
			  towns[i] = "Town_" + i;
			  graph.addTown(towns[i]);
		  }
		  
		  graph.addRoad(towns[1], towns[2], 3, "Road_1");
		  graph.addRoad(towns[1], towns[3], 3, "Road_2");
		  graph.addRoad(towns[1], towns[4], 2, "Road_3");
		  graph.addRoad(towns[2], towns[10], 4, "Road_4");
		  graph.addRoad(towns[3], towns[5], 6, "Road_5");
		  graph.addRoad(towns[3], towns[6], 5, "Road_6");
		  graph.addRoad(towns[4], towns[7], 4, "Road_7");
		  graph.addRoad(towns[4], towns[9], 4, "Road_8");
		  graph.addRoad(towns[5], towns[10], 10, "Road_9");
		  graph.addRoad(towns[6], towns[7], 5, "Road_10");
		  graph.addRoad(towns[7], towns[8], 6, "Road_11");
		  graph.addRoad(towns[8], towns[9], 6, "Road_12");
		  graph.addRoad(towns[9], towns[10], 8, "Road_13");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		towns = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		graph.addRoad(towns[2], towns[5], 1,"Road_0");
		roads = graph.allRoads();
		assertEquals("Road_0", roads.get(0));
		assertEquals("Road_1", roads.get(1));
		assertEquals("Road_10", roads.get(2));
		assertEquals("Road_11", roads.get(3));
		assertEquals("Road_12", roads.get(4));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_7", graph.getRoad(towns[4], towns[7]));
		assertEquals("Road_12", graph.getRoad(towns[8], towns[9]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_42"));
		graph.addTown("Town_42");
		assertEquals(true, graph.containsTown("Town_42"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("Town_100"));
		graph.addTown("Town_100");
		ArrayList<String> path = graph.getPath(towns[1],"Town_100");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_4"));
		assertEquals(false, graph.containsTown("Town_200"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(towns[3], towns[6]));
		assertEquals(false, graph.containsRoadConnection(towns[2], towns[8]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_8", roads.get(11));
		assertEquals("Road_9", roads.get(12));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(towns[7], towns[6]));
		graph.deleteRoadConnection(towns[7], towns[6], "Road_10");
		assertEquals(false, graph.containsRoadConnection(towns[6], towns[7]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_8"));
		graph.deleteTown(towns[8]);
		assertEquals(false, graph.containsTown("Town_8"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_10", roads.get(1));
		assertEquals("Town_2", roads.get(2));
		assertEquals("Town_5", roads.get(5));
		assertEquals("Town_9", roads.get(9));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(towns[10],towns[7]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("Town_10 via Road_4 to Town_2 4 mi",path.get(0).trim());
		  assertEquals("Town_2 via Road_1 to Town_1 3 mi",path.get(1).trim());
		  assertEquals("Town_1 via Road_3 to Town_4 2 mi",path.get(2).trim());
		  assertEquals("Town_4 via Road_7 to Town_7 4 mi",path.get(3).trim());
	}
}