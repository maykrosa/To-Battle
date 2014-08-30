package scenes;

import game.GamePanel;
import game.StaticContent;
import graphics.AnimatedSprite;
import managers.font.FontManager;
import managers.texture.TextureManager;
import objects.Map;
import objects.Unit;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import utils.PVector;

public class GamePlayScene extends Scene{

    public Map map;
    public Unit player;

	public GamePlayScene(GamePanel parent) {
    	this.parent = parent;
        
        map = new Map(TextureManager.mapa, 0, 0, 3840,2160, false);
        player = new Unit(TextureManager.player, 0, 0, 50, 70, true, new AnimatedSprite(6, 6, 200));

        attachSprite(map);
        attachSprite(player);
        
        position = new PVector(0, 0);
        zoom = 1f;
        
        width = parent.width;
        height = parent.height;
    }

	@Override
	public void keyboardInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.position.y -= 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.position.y += 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.position.x -= 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.position.x += 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			position.y -= 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			position.y += 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			position.x -= 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			position.x += 10;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			zoom += 0.01f;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			zoom -= 0.01f;
		}
		
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
					System.out.println("Posição de mundo: "+toWorldCoordinateX(mouseX)+" "+toWorldCoordinateY(mouseY));

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

	        FontManager.font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
	        FontManager.font2.drawString(100, 100, "NICE LOOKING FONTS!", Color.green);	
	        
	        /* Disable last color, default color white*/
	        GL11.glColor4f(1, 1, 1, 1);
        
        GL11.glPopMatrix();
	} 
}
