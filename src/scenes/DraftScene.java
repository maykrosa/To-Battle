package scenes;

import java.util.ArrayList;

import game.GamePanel;
import game.StaticContent;
import managers.Language;
import managers.font.FontManager;
import managers.texture.TextureManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import utils.PVector;
import utils.RenderGL;

public class DraftScene extends Scene{
	
	private float offLeft;
	private float offRight;
	private float offTop;
	
	private float spacingX;
	private float spacingY;
	
	private float widthCard;
	private float heightCard;
	
	private int currentCard;
	private ArrayList<Integer> selectedCards;
	
	public DraftScene(GamePanel parent) {
    	this.parent = parent;

        position = new PVector(0, 0);
        zoom = 1f;
        
        width = parent.width;
        height = parent.height;
        
    	offLeft = width*0.02f;
    	offRight = width*0.96f;
    	offTop = height*0.12f;
    	
    	spacingX = width*0.02f;
    	spacingY = height*0.04f;
    	
    	widthCard = width*0.08f;
    	heightCard = height *0.16f;
    	
    	currentCard = 0;
    	selectedCards = new ArrayList<>();
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
		for(int i=0; i<6; i++){
		    for(int j=0; j<4; j++){
		    	if(mouseX > offLeft+(spacingX+widthCard)*i && mouseX < offLeft+((spacingX+widthCard)*i)+widthCard &&
		    			mouseY > offTop+(spacingY+heightCard)*j && mouseY< offTop+((spacingY+heightCard)*j)+heightCard){
		    		currentCard = j*6+i;
		    	}
		    }
        }
		
		while (Mouse.next()) {
			/* Mouse pressed */
			if (Mouse.getEventButtonState()) {
				if (Mouse.getEventButton() == StaticContent.RIGHT_BUTTON) {
	
				}
				if (Mouse.getEventButton() == StaticContent.LEFT_BUTTON) {
					if(mouseX > width*0.4f && mouseX < width*0.6f && mouseY > height*0.90f && mouseY < height*0.96f){
						if(selectedCards.size() >= StaticContent.NUMBER_CARDS_TO_BATTLE){
							parent.currentScene = new GamePlayScene(parent);
							break;
						}
					}

			        for(int i=0; i<6; i++){
					    for(int j=0; j<4; j++){
					    	if(mouseX > offLeft+(spacingX+widthCard)*i && mouseX < offLeft+((spacingX+widthCard)*i)+widthCard &&
					    			mouseY > offTop+(spacingY+heightCard)*j && mouseY < offTop+((spacingY+heightCard)*j)+heightCard){
						    	/* Se já foi seleciona, entao deseleciona*/
						    	int result = alreadySelected(j*6+i);
						    	if(result != -1){
//						    		selectedCards[result] = -1;
						    		selectedCards.remove(result);
					    		/* Seleciona uma carta */
						    	}else{
							    	/*Já escolheu todas as cartas*/
							    	if(selectedCards.size() >= StaticContent.NUMBER_CARDS_TO_BATTLE)
							    		break;
						    		selectedCards.add(j*6+i);
	
						    	}
						    	
					    		break;
					    	}
					    }
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

        RenderGL.drawRectWithTexture(TextureManager.draftScreen, 0, 0, width, height, 1);
        
        RenderGL.drawRectWithTexture(TextureManager.panelCardInformation, offLeft+(spacingX+widthCard)*6, offTop, 
        		offRight, offTop+((spacingY+heightCard)*3)+heightCard, 1);
        
        RenderGL.drawRectWithTexture(TextureManager.cards[currentCard], width*0.68f, height*0.16f, 
        		width*0.9f, height*0.5f, 1);
        
        RenderGL.drawRectWithColor(new PVector(0.5f, 0.5f, 0.5f), width*0.64f, height*0.52f, width*0.94f, height*0.84f, 1);
        
        /* Ready button */
        RenderGL.drawRectWithColor(new PVector(0.5f, 0.5f, 0.5f), width*0.4f, height*0.90f, width*0.6f, height*0.96f, 1);
        
        for(int i=0; i<6; i++){
		    for(int j=0; j<4; j++){
		    	RenderGL.drawRectWithTexture(TextureManager.cards[j*6+i], offLeft+(spacingX+widthCard)*i, offTop+(spacingY+heightCard)*j, 
		    			offLeft+((spacingX+widthCard)*i)+widthCard, offTop+((spacingY+heightCard)*j)+heightCard, 1);
		    }
        }
        
        for(int i=0; i<selectedCards.size(); i++){
        	if(selectedCards.get(i) > -1){
	        	RenderGL.drawRectWithColor(new PVector(1, 0, 0), offLeft+(spacingX+widthCard)*(selectedCards.get(i)%6), offTop+(spacingY+heightCard)*(selectedCards.get(i)/6), 
		    			offLeft+((spacingX+widthCard)*(selectedCards.get(i)%6))+widthCard, offTop+((spacingY+heightCard)*(selectedCards.get(i)/6))+heightCard, 0.5f);
        	}
        }
        
        FontManager.font.drawString(width*0.66f, height*0.54f, Language.getText(1), Color.black);
        
        int x = (width - FontManager.font.getWidth(Language.getText(2))) / 2;
		int y = (int) (height * 0.91f);
        FontManager.font.drawString(x, y, Language.getText(2), Color.black);
        FontManager.font2.drawString(0, 0, "", Color.green);	
        
        /* Disable last color, default color white*/
        GL11.glColor4f(1, 1, 1, 1);
    
    GL11.glPopMatrix();
	}
	
	private int alreadySelected(int number){
		for(int i=0; i<selectedCards.size(); i++){
			if(number == selectedCards.get(i)){
				return i;
			}
		}
		return -1;
	}

}
