package objects;

import java.util.LinkedList;

import objects.components.MouseInput;

import utils.PVector;

public class Path implements MouseInput{
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

	@Override
	public void onClick(float mouseX, float mouseY) {

	}

	@Override
	public void onOver(float mouseX, float mouseY) {

	}
}
