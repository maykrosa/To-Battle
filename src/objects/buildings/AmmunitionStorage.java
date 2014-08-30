package objects.buildings;

import utils.RenderGL;
import game.StaticContent;
import graphics.Sprite;
import managers.texture.GTexture;

public class AmmunitionStorage extends Sprite{
	
	public float ammunition;
	public float ammunitionRegen;
	
	public AmmunitionStorage(GTexture texture, float x, float y, float width, float height, boolean sort){
		super(texture, x, y, width, height, sort);
		
		ammunition = StaticContent.AMMUNITIION_BASE;
		ammunitionRegen = StaticContent.AMMUNITIION_REGEN_BASE;
	}

	@Override
	public void update(int difTime) {
		ammunition += ammunitionRegen*difTime;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}
