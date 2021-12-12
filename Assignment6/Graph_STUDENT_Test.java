


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town[] towns;

	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		  towns = new Town[11];
		  
		  for (int i = 1; i <= 10; i++) {
			  towns[i] = new Town("Town_" + i);
			  graph.addVertex(towns[i]);
		  }
		  
		  graph.addEdge(towns[1], towns[2], 3, "Road_1");
		  graph.addEdge(towns[1], towns[3], 3, "Road_2");
		  graph.addEdge(towns[1], towns[4], 2, "Road_3");
		  graph.addEdge(towns[2], towns[10], 4, "Road_4");
		  graph.addEdge(towns[3], towns[5], 6, "Road_5");
		  graph.addEdge(towns[3], towns[6], 5, "Road_6");
		  graph.addEdge(towns[4], towns[7], 4, "Road_7");
		  graph.addEdge(towns[4], towns[9], 4, "Road_8");
		  graph.addEdge(towns[5], towns[10], 10, "Road_9");
		  graph.addEdge(towns[6], towns[7], 5, "Road_10");
		  graph.addEdge(towns[7], towns[8], 6, "Road_11");
		  graph.addEdge(towns[8], towns[9], 6, "Road_12");
		  graph.addEdge(towns[9], towns[10], 8, "Road_13");
		  
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		towns = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(towns[4], towns[7],4, "Road_12"), graph.getEdge(towns[4], towns[7]));
		assertEquals(new Road(towns[7], towns[8],6, "Road_4"), graph.getEdge(towns[7], towns[8]));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(towns[2], towns[5]));
		assertEquals(false, graph.containsEdge(towns[1], towns[6]));
		graph.addEdge(towns[2], towns[5], 4, "Road_14");
		graph.addEdge(towns[1], towns[6], 7, "Road_15");
		assertEquals(true, graph.containsEdge(towns[2], towns[5]));
		assertEquals(true, graph.containsEdge(towns[1], towns[6]));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_14");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(towns[3], towns[5]));
		assertEquals(false, graph.containsEdge(towns[3], towns[9]));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_2")));
		assertEquals(false, graph.containsVertex(new Town("Town_14")));
	}

	@Test
	public void testEdgeSet() {
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : graph.edgeSet()) {
			roadArrayList.add(road.getName());
		}
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_10", roadArrayList.get(1));
		assertEquals("Road_11", roadArrayList.get(2));
		assertEquals("Road_12", roadArrayList.get(3));
		assertEquals("Road_13", roadArrayList.get(4));
		assertEquals("Road_7", roadArrayList.get(10));
		assertEquals("Road_9", roadArrayList.get(12));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(towns[3]);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_2", roadArrayList.get(0));
		assertEquals("Road_5", roadArrayList.get(1));
		assertEquals("Road_6", roadArrayList.get(2));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(towns[4], towns[9]));
		graph.removeEdge(towns[4], towns[9], 4, "Road_8");
		assertEquals(false, graph.containsEdge(towns[4], towns[9]));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(towns[3]));
		graph.removeVertex(towns[3]);
		assertEquals(false, graph.containsVertex(towns[3]));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true, roads.contains(towns[2]));
		assertEquals(true, roads.contains(towns[5]));
		assertEquals(false, roads.contains(new Town("Town_11")));
		assertEquals(true, roads.contains(towns[7]));
		assertEquals(true, roads.contains(towns[3]));
		assertEquals(false, roads.contains(new Town("Town_18")));
	}

	 @Test
	 public void testShortestPath() {
		 ArrayList<String> path9To5 = graph.shortestPath(towns[9], towns[5]);
		 assertEquals(path9To5.get(0), "Town_9 via Road_8 to Town_4 4 mi");
		 assertEquals(path9To5.get(1), "Town_4 via Road_3 to Town_1 2 mi");
		 assertEquals(path9To5.get(2), "Town_1 via Road_2 to Town_3 3 mi");
		 assertEquals(path9To5.get(3), "Town_3 via Road_5 to Town_5 6 mi");
	 }
}