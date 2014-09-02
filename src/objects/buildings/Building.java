package objects.buildings;

import graphics.Sprite;
import managers.texture.GTexture;

public class Building extends Sprite{

	public float maxHp;
	public float hp;
	
	public Building(GTexture texture, boolean sort){
		super(texture, 0, 0, 0, 0, sort);
	}

	@Override
	public void update(int difTime) {}
	@Override
	public void render() {}
}

