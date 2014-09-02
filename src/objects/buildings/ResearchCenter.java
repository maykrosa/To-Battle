package objects.buildings;

import game.StaticContent;
import managers.texture.GTexture;
import utils.RenderGL;

public class ResearchCenter extends Building{
	
	public ResearchCenter(GTexture texture, boolean sort){
		super(texture, sort);
		
		maxHp = StaticContent.RESEARCH_CENTER_HP;
		hp = maxHp;
	}

	@Override
	public void update(int difTime) {
		if(hp < 0)
			active = false;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}
}