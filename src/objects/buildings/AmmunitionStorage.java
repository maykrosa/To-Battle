package objects.buildings;

import utils.RenderGL;
import game.StaticContent;
import managers.texture.GTexture;

public class AmmunitionStorage extends Building{
	
	public float ammunition;
	public float ammunitionRegen;
	
	public AmmunitionStorage(GTexture texture, boolean sort){
		super(texture, sort);
		
		maxHp = StaticContent.AMMUNITION_STORAGE_HP;
		hp = maxHp;
		
		ammunition = StaticContent.AMMUNITIION_BASE;
		ammunitionRegen = StaticContent.AMMUNITIION_REGEN_BASE;
	}

	@Override
	public void update(int difTime) {
		ammunition += ammunitionRegen*difTime;
		
		if(hp < 0)
			active = false;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}
