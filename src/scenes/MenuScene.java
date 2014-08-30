package scenes;

import game.GamePanel;
import game.StaticContent;
import managers.Language;
import managers.font.FontManager;
import managers.sound.SoundManager;
import managers.texture.TextureManager;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import utils.PVector;
import utils.RenderGL;

public class MenuScene extends Scene{
	
	public MenuScene(GamePanel parent) {
    	this.parent = parent;

        position = new PVector(0, 0);
        zoom = 1f;
        
        width = parent.width;
        height = parent.height;
    }
	
	@Override
	public void keyboardInput() {
        while(Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                if(Keyboard.getEventKey() == Keyboard.KEY_F){
                    parent.setDisplayMode(width, height, !Display.isFullscreen());
                }
            }else{

            }
        }
	}

	@Override
	public void mouseInput() {
		mouseX = Mouse.getX();
		mouseY = (Mouse.getY()-parent.height)*-1;
		
		while (Mouse.next()) {
			/* Mouse pressed */
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == StaticContent.RIGHT_BUTTON) {
	
				}
				if (Mouse.getEventButton() == StaticContent.LEFT_BUTTON) {
					/* Start Game button*/
					if (mouseX > (width * 0.35f) && mouseX <  (width * 0.65f)
							&&  mouseY > (height * 0.68f) && mouseY < (height * 0.78f)) {
						parent.currentScene = new SelectStageScene(parent);
					}
					
					/* Language button*/
					if (mouseX > (width * 0.8f) && mouseX < (width * 0.8f) + (width * 0.05f)
							&&  mouseY > (height * 0.05f) && mouseY < (height * 0.05f) + (height * 0.1f)) {
						Language.loadLanguage(Language.getCurrentLanguage() == Language.ENGLISH ? Language.PORTUGUES
								: Language.ENGLISH);
					}
					
					/*Sound Track button */
					if (mouseX > (width * 0.85f) && mouseX < (width * 0.85f) + (width * 0.05f)
							&&  mouseY > (height * 0.05f) && mouseY < (height * 0.05f) + (height * 0.1f)) {
						SoundManager.soundTrackVolume = SoundManager.soundTrackVolume == 1 ? 0 : 1;
					}

					if (mouseX > (width * 0.9f) && mouseX < (width * 0.9f) + (width * 0.05f)
							&&  mouseY > (height * 0.05f) && mouseY < (height * 0.05f) + (height * 0.1f)) {
						SoundManager.sfxVolume = SoundManager.sfxVolume == 1 ? 0 : 1;
					}
				}

				if (Mouse.getEventButton() == StaticContent.MIDDLE_BUTTON) {

				}
			/* Mouse release */
			} else {
				if (Mouse.getEventButton() == StaticContent.RIGHT_BUTTON) {

				}
				if (Mouse.getEventButton() == StaticContent.LEFT_BUTTON) {

				}
				if (Mouse.getEventButton() == StaticContent.MIDDLE_BUTTON) {

				}
			}
		}
	}
	
    @Override
    public void update(int difTime) {
    	super.update(difTime);
    }  
    
    @Override
    public void render() {
    	super.render();
    	  
    }

    @SuppressWarnings("deprecation")
	@Override
	public void renderInterface() {
		GL11.glPushMatrix();
	 	GL11.glLoadIdentity(); // Load the Identity Matrix to reset our drawing locations  
	 	RenderGL.drawRectWithTexture(TextureManager.menuScreen, 0, 0, width, height, 1);
        
        RenderGL.drawRectWithTexture(Language.getCurrentLanguage() == Language.ENGLISH ?
        		TextureManager.flagEnglish : TextureManager.flagPortuguese,  (int) (width * 0.8f),(int) (height * 0.05f), (int) (width * 0.85f),(int) (height * 0.15f), 1);
        RenderGL.drawRectWithTexture(SoundManager.soundTrackVolume == 1 ? 
        		TextureManager.soundTrackOn : TextureManager.soundTrackOff, (int) (width * 0.85f),(int) (height * 0.05f), (int) (width * 0.9f),(int) (height * 0.15f), 1);
        RenderGL.drawRectWithTexture(SoundManager.sfxVolume == 1 ? 
        		TextureManager.sfxOn : TextureManager.sfxOff, (int) (width * 0.9f), (int) (height * 0.05f),(int) (width * 0.95f), (int) (height * 0.15f), 1);

		int x = (width - FontManager.font.getWidth(Language.getText(0))) / 2;
		int y = (int) (height * 0.7f);
        FontManager.font.drawString(x, y, Language.getText(0), Color.yellow);
        FontManager.font2.drawString(0, 0, "", Color.green);	
        
        /* Disable last color, default color white*/
        GL11.glColor4f(1, 1, 1, 1);
    
    GL11.glPopMatrix();
	}
}
