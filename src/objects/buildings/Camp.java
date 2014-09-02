package objects.buildings;

import game.StaticContent;
import managers.texture.GTexture;
import utils.RenderGL;

public class Camp extends Building{
	
	public float energy;
	public float energyRegen;
	
	public int[] cards;
	
	public Camp(GTexture texture, boolean sort, int[] cards){
		super(texture, sort);
		
		maxHp = StaticContent.CAMP_HP;
		hp = maxHp;
		
		energy = StaticContent.ENERGY_BASE;
		energyRegen = StaticContent.AMMUNITIION_REGEN_BASE;
	}

	@Override
	public void update(int difTime) {
		energy += energyRegen*difTime;
		
		if(hp < 0)
			active = false;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}
