/**
 * This class represents a Road object. Its main purpose is to connect two Towns with a certain weight value
 * acting as the Edges in a Graph of Towns. Most of the methods are simple getters and setters, with a few 
 * small overrides on methods like equals, compareTo, and toString
 * @author Evan Song
 *
 */
public class Road implements Comparable<Road> {
	private Town source;
	private Town destination;
	private int weight;
	private String name;
	public Road(Town source, Town destination, int degrees, String name) {
		if(source.compareTo(destination) <= 0) {
			this.source = source;
			this.destination = destination;
		} else {
			this.source = destination;
			this.destination = source;
		}
		source.getAdjacent().add(destination);
		destination.getAdjacent().add(source);
		this.weight = degrees;
		this.name = name;
		source.getEdges().add(this);
		destination.getEdges().add(this);
	}
	public Road(Town source, Town destination, String name) {
		if(source.compareTo(destination) <= 0) {
			this.source = source;
			this.destination = destination;
		} else {
			this.source = destination;
			this.destination = source;
		}
		source.getAdjacent().add(destination);
		destination.getAdjacent().add(source);
		source.getEdges().add(this);
		destination.getEdges().add(this);
		this.weight = 1;
		this.name = name;
	}
	/**
	 * Simple method that checks whether either the Road's source or
	 * destination is equal to the given Town.
	 * @param town The town to be checked for containment
	 * @return boolean true if the Road's source or destination equals the given town, false if not
	 */
	public boolean contains(Town town) {
		if(source.equals(town) || destination.equals(town)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Simple toString override, returns the Road in string form as given in the provided files;
	 * ie: I-95,25;Bethesda;Chevy-Chase
	 * @return The Road in string form, with all attributes included
	 */
	public String toString() {
		return name+","+weight+";"+source+";"+destination;
	}
	
	/**
	 * A simple getter method for the Road's name
	 * @return String The Road's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * A simple getter method for the Road's source Town
	 * @return Town The Road's source Town
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * A simple getter method for the Road's destination Town
	 * @return Town The Road's destination Town
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * A simple getter method for the Road's weight
	 * @return int The Road's Weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Simple comparareTo method override, checking whether the tow roads have the same source and destination as well as name
	 * @param r The Road to be compared to
	 * @return 0 if the two Roads are equal, or a non-zero number if not
	 */
	@Override
	public int compareTo(Road r) {
		if (this.equals(r) && name.equals(r.getName())) {
			return 0;
		}
		return name.compareTo(r.getName());
	}
	
	/**
	 * Simple equals method override, based only on the source and destinations
	 * @param o The object being checked for equality
	 * @return true if the two objects are both Roads connecting the same two Towns, or false if not
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Road)) {
			return false;
		}
		Road r = (Road) o;
		if(source.equals(r.getSource()) && destination.equals(r.getDestination())) {
			return true;
		}
		return false;
		
	}
}
