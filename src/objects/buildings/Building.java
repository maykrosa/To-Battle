package objects.buildings;

import objects.components.MouseInput;
import graphics.Sprite;
import managers.texture.GTexture;

public abstract class Building extends Sprite implements MouseInput{

	public float maxHp;
	public float hp;
	
	public boolean dialogBox;
	
	public Building(GTexture texture, boolean sort){
		super(texture, 0, 0, 0, 0, sort);
		
		dialogBox = false;
	}
	
	@Override
	public void update(int difTime) {}
	
	@Override
	public void render() {}
	
	public abstract void renderInterface();
}

