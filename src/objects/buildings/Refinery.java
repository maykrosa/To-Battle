package objects.buildings;

import game.StaticContent;
import managers.texture.GTexture;
import utils.RenderGL;

public class Refinery extends Building{
	
	public float fuel;
	public float fuelRegen;
	
	public Refinery(GTexture texture,  boolean sort){
		super(texture,  sort);
		
		maxHp = StaticContent.REFINETY_HP;
		hp = maxHp;
		
		fuel = StaticContent.FUEL_BASE;
		fuelRegen = StaticContent.FUEL_REGEN_BASE;
	}

	@Override
	public void update(int difTime) {
		fuel += fuelRegen*difTime;
		
		if(hp < 0)
			active = false;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}