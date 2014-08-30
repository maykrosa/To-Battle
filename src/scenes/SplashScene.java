package scenes;

import game.GamePanel;
import game.StaticContent;
import managers.texture.TextureManager;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import utils.PVector;
import utils.RenderGL;

public class SplashScene extends Scene{

	private int timer;
	private int timerInSceen;
	
	public SplashScene(GamePanel parent) {
    	this.parent = parent;

        position = new PVector(0, 0);
        zoom = 1f;
        
        width = parent.width;
        height = parent.height;
        
        timer = 0;
        timerInSceen = 500;
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
    	
    	timer += difTime;
    	if(timer > timerInSceen)
    		parent.currentScene = new MenuScene(parent);
    }  
    
    @Override
    public void render() {
    	super.render();
    }

	@Override
	public void renderInterface() {
		GL11.glPushMatrix();
	 	GL11.glLoadIdentity(); // Load the Identity Matrix to reset our drawing locations  

        RenderGL.drawRectWithTexture(TextureManager.splashScreen, 0, 0, width, height, 1);
        
        /* Disable last color, default color white*/
        GL11.glColor4f(1, 1, 1, 1);
    
    GL11.glPopMatrix();
	}

}
