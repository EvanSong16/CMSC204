import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TownGraphManager implements TownGraphManagerInterface {
	Graph graph = new Graph();
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		try {
			graph.addEdge(getTown(town1), getTown(town2), weight, roadName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		try {
			return graph.getEdge(getTown(town1), getTown(town2)).getName();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		try {
			return graph.addVertex(new Town(v));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		try {
			for(Town t : graph.towns) {
				if(t.getName().equals(name)) {
					return t;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		try {
			for(Town t : graph.towns) {
				if(t.getName().equals(v)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		try {
			return graph.containsEdge(getTown(town1), getTown(town2));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> result = new ArrayList<>();
		for(Road r : graph.roads) {
			result.add(r.getName());
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		try {
			graph.removeEdge(getTown(town1), getTown(town2), -1, road);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		try {
			graph.removeVertex(getTown(v));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> result = new ArrayList<>();
		for(Town t : graph.towns) {
			result.add(t.getName());
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		try {
			return graph.shortestPath(getTown(town1), getTown(town2));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Populates the graph with the contents of a file
	 * @param f The file to be read and filled into the graph, which should have each line in the format "roadName,weight;town1;town2"
	 * @throws FileNotFoundException
	 */
	public void populateTownGraph(File f) throws FileNotFoundException {
		Scanner scan;
		String[] firstSplit;
		String[] secondSplit;
		scan = new Scanner(f);
		String road;
		while (scan.hasNextLine()) {
			road = scan.nextLine();
			firstSplit = road.split(",");
			secondSplit = firstSplit[1].split(";");
			addTown(secondSplit[1]);
			addTown(secondSplit[2]);
			addRoad(secondSplit[1], secondSplit[2], Integer.parseInt(secondSplit[0]), firstSplit[0]);
		}
		scan.close();
	}
}
