package objects;

import java.util.LinkedList;

import utils.PVector;

public class Path {
	public int source;
	public int destination;
	public int numberWaypoints;
	public LinkedList<PVector> waypoints;
	
	public Path(){
		source = 0;
		destination = 0;
		numberWaypoints = 0;
		waypoints = new LinkedList<>();
	}
}
