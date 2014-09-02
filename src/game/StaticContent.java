package game;

/**
* StaticContent, abstract class. Scene is a struct of game objects.
* 
* @author Gilvanei Gregorio
* @version 1.0
*/
public class StaticContent {

    public static final int FPS_LIMIT = 60;
    
	public static final int LEFT_BUTTON = 0;
	public static final int RIGHT_BUTTON = 1;
	public static final int MIDDLE_BUTTON = 2;
	
	/** Width of screen */
	public static int widthScreen = 1280;
	/** Height of screen */
	public static int heightScreen = 720;
	
	public static final float CAMP_HP = 100;
	public static final float REFINETY_HP = 100;
	public static final float AMMUNITION_STORAGE_HP = 100;
	public static final float RESEARCH_CENTER_HP = 100;
	
	public static final float AMMUNITIION_BASE = 100;
	public static final float AMMUNITIION_REGEN_BASE = 1.0f;
	
	public static final float ENERGY_BASE = 100;
	public static final float ENERGY_REGEN_BASE = 1.0f;
	
	public static final float FUEL_BASE = 100;
	public static final float FUEL_REGEN_BASE = 1.0f;
}
