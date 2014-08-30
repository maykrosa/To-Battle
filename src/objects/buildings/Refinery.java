package objects.buildings;

import utils.RenderGL;
import game.StaticContent;
import graphics.Sprite;
import managers.texture.GTexture;

public class Refinery extends Sprite{
	
	public float fuel;
	public float fuelRegen;
	
	public Refinery(GTexture texture, float x, float y, float width, float height, boolean sort){
		super(texture, x, y, width, height, sort);
		
		fuel = StaticContent.FUEL_BASE;
		fuelRegen = StaticContent.FUEL_REGEN_BASE;
	}

	@Override
	public void update(int difTime) {
		fuel += fuelRegen*difTime;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}