import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * A graph of Towns and Roads, able to store vertexes and edges and to implement the Dijkstra's
 * Shortest Path algorithm in order to find the shortest distance and path between two Towns contained by the Graph.
 * @author Evan Song
 *
 */
public class Graph implements GraphInterface<Town, Road> {
	public HashSet<Town> towns;
	public HashSet<Road> roads;
	public HashSet<Town> dijkstraSet;
	private Town iterator;
	private Road weightFinder;
	public Graph() {
		towns = new HashSet<>();
		roads = new HashSet<>();
	}
	/**
	 * Checks each of the Roads contained in the graph and compares their sources and destinations
	 * with the Towns given as input. If a road is found with the same sources and destinations 
	 * that were given, returns that road.
	 * @param sourceVertex One of the Towns being searched for in the Road
	 * @param destinationVertex The other Town being searched for in the Road
	 * @return Road The Road containing both Towns, or null if either Town was null or the Road doesn't exist in the Graph
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}
		for(Road r : roads) {
			if(r.getSource().equals(sourceVertex)) {
				if(r.getDestination().equals(destinationVertex)) {
					return r;
				}
			}
			if(r.getSource().equals(destinationVertex)) {
				if(r.getDestination().equals(sourceVertex)) {
					return r;
				}
			}
		}
		return null;
	}
	
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException("Can't connect a town to a null!");
		}
		if(!(containsVertex(sourceVertex) && containsVertex(destinationVertex))) {
			throw new IllegalArgumentException("Error 404: Town not found");
		}
		Road edge = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(edge);
		return edge;
	}

    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		if(v == null) {
			throw new NullPointerException("Can't add a null vertex");
		}
		if(containsVertex(v)) {
			return false;
		}
		towns.add(v);
		return true;
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null) {
			return false;
		}
		for(Road r : roads) {
			if(r.getSource().equals(sourceVertex)) {
				if(r.getDestination().equals(destinationVertex)) {
					return true;
				}
			}
			if(r.getSource().equals(destinationVertex)) {
				if(r.getDestination().equals(sourceVertex)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		if(v == null) {
			return false;
		}
		for(Town t : towns) {
			if(t.getName().equals(v.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		if(!containsVertex(vertex)) {
			throw new IllegalArgumentException("Error 404: Vertex not found");
		}
		if(vertex == null) {
			throw new NullPointerException("Vertex is null. That's not good...");
		}
		return vertex.getEdges();
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}
		for(Road r : roads) {
			if((r.getSource().equals(sourceVertex) && r.getDestination().equals(destinationVertex)) || (r.getSource().equals(destinationVertex) && r.getDestination().equals(sourceVertex))) {
				roads.remove(r);
				return r;
			}
		}
		return null;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if(v == null) {
			return false;
		}
		if(containsVertex(v)) {
			towns.remove(v);
			return true;
		}
		return false;
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */   
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		String connection;
		ArrayList<String> result = new ArrayList<>();
		dijkstraShortestPath(sourceVertex);
		for(int i = 1; i < destinationVertex.path.size(); i++) {
			connection = "";
			connection += destinationVertex.path.get(i-1);
			connection += " via ";
			connection += getEdge(destinationVertex.path.get(i-1), destinationVertex.path.get(i)).getName();
			connection += " to ";
			connection += destinationVertex.path.get(i);
			connection += " ";
			connection += getEdge(destinationVertex.path.get(i-1), destinationVertex.path.get(i)).getWeight();
			connection += " mi";
			result.add(connection);
		}
		return result;
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		if(sourceVertex == null) {
			throw new NullPointerException("Can't find the shortest path from null");
		}
		if(!containsVertex(sourceVertex)) {
			throw new IllegalArgumentException("Can't find the shortest path to a vector that isn't in the graph");
		}
		dijkstraSet = new HashSet<>();
		for(Town t : towns) {
			t.path = new ArrayList<>();
			dijkstraSet.add(t);
			t.setWeight(Integer.MAX_VALUE);
		}
		sourceVertex.setWeight(0);
		while(dijkstraSet.size() > 0) {
			iterator = new Town("ITERATOR");
			iterator.setWeight(Integer.MAX_VALUE);
			for(Town t: dijkstraSet) {
				if(t.getWeight() < iterator.getWeight()) {
					iterator = t;
				}
			}
			if(iterator.getWeight() == Integer.MAX_VALUE) {
				return;
			}
			if(iterator.path.size() == 0) {
				iterator.path.add(sourceVertex);
			}
			for(Town t : dijkstraSet) {
				if(iterator.getAdjacent().contains(t)) {
					weightFinder = getEdge(iterator, t);
					if((iterator.getWeight() + weightFinder.getWeight()) < t.getWeight()) {
						t.setWeight(iterator.getWeight() + weightFinder.getWeight());
						t.path = new ArrayList<>();
						for(Town o : iterator.path) {
							t.path.add(o);
						}
						t.path.add(t);
					}
				}
			}
			dijkstraSet.remove(iterator);
		}
	}
	
}
