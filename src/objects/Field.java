package objects;

import java.awt.Rectangle;

import objects.components.MouseInput;

public class Field implements MouseInput{
	
	public int resource;
	public Rectangle position;
	
	public Field() {
		resource = 0;
		position = new Rectangle();
	}

	@Override
	public void onClick(float mouseX, float mouseY) {
		
	}

	@Override
	public void onOver(float mouseX, float mouseY) {

	}
}
