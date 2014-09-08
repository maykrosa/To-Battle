package objects.buildings;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import utils.PVector;
import utils.RenderGL;
import game.StaticContent;
import managers.Language;
import managers.font.FontManager;
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
		ammunition += ammunitionRegen*difTime/1000;
		
		if(hp < 0)
			active = false;
	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void renderInterface() {
		if(dialogBox){
			RenderGL.drawRectWithColor(new PVector(1, 1, 1), (position.x+width/2)-300, position.y-200, (position.x+width/2)+300, position.y-20, 0.8f);
			
			FontManager.font.drawString((position.x+width/2)-300,  position.y-200, Language.getText(9)+": "+hp, Color.black);
	        FontManager.font.drawString((position.x+width/2)-300,  position.y-150, Language.getText(3)+": "+ammunition, Color.black);
	        FontManager.font.drawString((position.x+width/2)-300,  position.y-100, Language.getText(4)+": "+ammunitionRegen, Color.black);
	        FontManager.font2.drawString(0, 0, "", Color.black);	
	        
	        /* Disable last color, default color white*/
	        GL11.glColor4f(1, 1, 1, 1);
		}
	}

	@Override
	public void onClick(float mouseX, float mouseY) {
		if(collisionBox.contains((int)mouseX, (int)mouseY)){
			dialogBox = !dialogBox;
		}
	}
	
	@Override
	public void onOver(float mouseX, float mouseY) {

	}
}
