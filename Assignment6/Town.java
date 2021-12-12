import java.util.ArrayList;
import java.util.HashSet;
/**
 * This class represents a Town object, which holds a few attributes including a name,
 * references to the towns and roads connected to itself, and a path and weight for 
 * Dijkstra's Shortest Path Algorithm
 * @author Evan Song
 *
 */
public class Town implements Comparable<Town> {
	private String name;
	private HashSet<Town> adjacent;
	private HashSet<Road> edges;
	public ArrayList<Town> path;
	private int dijkstraWeight;
	
	public Town(String name) {
		this.name = name;
		adjacent = new HashSet<>();
		edges = new HashSet<>();
		path = new ArrayList<>();
		dijkstraWeight = Integer.MAX_VALUE;
	}
	public Town(Town templateTown) {
		this.name = templateTown.getName();
		adjacent = new HashSet<>();
		for(Town o : templateTown.getAdjacent()) {
			adjacent.add(o);
		}
		edges = new HashSet<>();
		for(Road o : templateTown.getEdges()) {
			edges.add(o);
		}
		path = templateTown.path;
		dijkstraWeight = templateTown.getWeight();
	}
	
	/**
	 * Simple getter method for the Town's name
	 * @return String The name of the town
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A simple comparison between two towns, essentially just comparing their names
	 * @param o The Town to be compared with the current Town
	 * @return int 0 if the two towns are equal, or a non-zero number if not
	 */
	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.getName());
	}
	
	/**
	 * A simple override of the toString method, returning name instead of a reference
	 * @return String The name of the town, exactly the same as the getName method
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * A simple override of the hashCode method, making sure that it fulfills the contract
	 * @return int The hashcode of the Town, based on its name
	 */
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * Very simple equals method override, reliant only on Town name
	 * @param o The town to be compared to this one
	 * @return boolean true if the two towns share the same name, false if not
	 */
	public boolean equals(Town o) {
		return name.equals(o.getName());
	}
	
	/**
	 * Simple getter method for the Town's adjacent Towns
	 * @return HashSet<Town> A set of all towns adjacent to the current one
	 */
	public HashSet<Town> getAdjacent() {
		return adjacent;
	}
	
	/**
	 * Simple getter method for the Town's adjacent Roads
	 * @return HashSet<Road> A set of the roads connecting to the current Town
	 */
	public HashSet<Road> getEdges() {
		return edges;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Simple getter method for the Town's weight
	 * @return int The distance from the town to a specific source Town, used
	 * in Dijkstra's Shortest Path algorithm
	 */
	public int getWeight() {
		return dijkstraWeight;
	}
	
	/**
	 * Simple setter method for the Town's weight
	 * @param weight The new distance from a certain source Town, used
	 * in Dijkstra's Shortest Path algorithm
	 */
	public void setWeight(int weight) {
		dijkstraWeight = weight;
	}
}
