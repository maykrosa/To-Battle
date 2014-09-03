package scenes;

import game.GamePanel;
import graphics.Sprite;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import utils.PVector;

/**
* Scene, abstract class. Scene is a struct of game objects.
* 
* @author Gilvanei Gregorio
* @version 1.1
*/
public abstract class Scene {
    
	/** Parent, this object controla the game loop, and all game. */
    public GamePanel parent;
    
    /** Position of scene */
    public PVector position;
    
    /** Zoom of scene */
    public float zoom = 1f;
    
    public int width;
    public int height;
    
    protected int mouseX;
    protected int mouseY;
    
    /** List with all sprites of scene */
    private LinkedList<Sprite> graphicsElements = new LinkedList<Sprite>();
    
    /** Auxiliary list, used to sort the sprites that will draw */
    private LinkedList<Sprite> drawlist = new LinkedList<Sprite>();

    /** 
     * Auxiliary list, sprite will add in graphic elements of scene.
     * 
     * @param Sprite s
     */
    public void attachSprite(Sprite s){
    	graphicsElements.add(s);
    }
    
    /** 
     * Auxiliary list, sprite will remove in graphic elements of scene.
     * 
     * @param Sprite s
     */
    public void detachSprite(Sprite detachS){
    	for (Iterator<Sprite> iterator = graphicsElements.iterator(); iterator.hasNext();) {
			Sprite s = (Sprite)iterator.next();
			
			/* Find and remove of element list */
			if(s == detachS){
				graphicsElements.remove(s);
				break;
			}
    	}
    }
    
    /** 
     * keyboardInput abstract method. keyboard input;
     */
    public abstract void keyboardInput();
    
    /** 
     * mouseInput abstract method. mouse input;
     */
    public abstract void mouseInput();
    
    /** 
     * update, traverses the element list and update each element.
     * 
     * @param int difTime
     */
    public void update(int difTime){
    	for (Iterator<Sprite> iterator = graphicsElements.iterator(); iterator.hasNext();) {
			Sprite s = (Sprite)iterator.next();
			s.update(difTime);
    	}
    }
    
    /** 
     * render, scale and tranlate the scene, traverses the draw list and sort it,
     * then traverses the draw list list and render each element.
     */
    public void render(){
		GL11.glPushMatrix();
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        /* Load the Identity Matrix to reset our drawing locations */
        GL11.glLoadIdentity(); 
        
        /* Zoom  */
    	GL11.glScalef(zoom, zoom, 0);
    	
    	 /* Position  */
    	GL11.glTranslatef(parent.width/zoom/2-position.x, parent.height/zoom/2-position.y, 0);

    	/* Prepares draw list with graphics elements list to sort and render */
    	drawlist.clear();
		drawlist.addAll(graphicsElements);

		/* Sort draw list, with respect to y of sprite */
		Collections.sort(drawlist, new Comparator<Sprite>() {
			@Override
			public int compare(Sprite s1, Sprite s2) {
				/* Sprites that not sort, like backgrounds or maps */
				if(!s1.sort)
					return -1;
				if(!s2.sort)
					return 1;
	
				
		        if (s1.position.y+s1.height < s2.position.y+s2.height) {
		            return -1;
		        }else {
		            return 1;
		        }
			}
		});
		
		/* render each sprite already ordered of draw list */
    	for (Iterator<Sprite> iterator = drawlist.iterator(); iterator.hasNext();) {
			Sprite s = iterator.next();
			s.render();
			System.out.print(" "+s.sort);
    	}
    	System.out.println();
    	
    	GL11.glPushMatrix();
    };
    
    /** 
     * renderInterface abstract method. Render interface, hud;
     */
    public abstract void renderInterface();
    
    /** 
     * toWorldCoordinateX, convert mouse position in world position.
     * 
     * @param mouseX
     * 
     * @return float, position x in world coordinate.
     */
    public float toWorldCoordinateX(float mouseX){
    	return (mouseX/zoom) - (parent.width/zoom/2) + position.x;
    }
    
    /** 
     * toWorldCoordinateY, convert mouse position in world position.
     * 
     * @param mouseY
     * 
     * @return float, position y in world coordinate.
     */
    public float toWorldCoordinateY(float mouseY){		
    	mouseY = (mouseY-parent.height)*-1;
    	return ((mouseY/zoom) - (parent.height/zoom/2) - position.y)*-1;
    }
}
