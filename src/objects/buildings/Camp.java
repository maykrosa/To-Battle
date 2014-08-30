package objects.buildings;

import utils.RenderGL;
import managers.texture.GTexture;
import game.StaticContent;
import graphics.Sprite;

public class Camp extends Sprite{
	
	public float energy;
	public float energyRegen;
	
	public Camp(GTexture texture, float x, float y, float width, float height, boolean sort){
		super(texture, x, y, width, height, sort);
		
		energy = StaticContent.ENERGY_BASE;
		energyRegen = StaticContent.AMMUNITIION_REGEN_BASE;
	}

	@Override
	public void update(int difTime) {
		energy += energyRegen*difTime;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}
