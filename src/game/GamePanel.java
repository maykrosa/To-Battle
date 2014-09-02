package game;

import managers.Language;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import scenes.Scene;
import scenes.SplashScene;
import utils.RenderGL;

/**
* GamePanel, camade of program that control de game, your game loop, 
* update scenes and render scenes.
* 
* @author Gilvanei Gregorio
* @author Gustavo Gregorio
* 
* @version 1.0
*/
public class GamePanel {

	/** Singleton , instance of game panel */
	public static GamePanel instance;
	
	/** Frame per second */
	public static int FPS;
	
	/** Conditional of get out main thread */
	public boolean running = false;
	
	public int width = StaticContent.widthScreen;
	public int height = StaticContent.heightScreen;
    
	/** Current time of system */
	private long currentTime = 0;
	/** Time in last cycle of system */
	private int lastTime = 0;
	/** Differece of current time of system and time in last cycle of system */
	private int difTime = 0;
	/** Some fps */
	private int sfps = 0;

    
    /* Scene group */
    public Scene currentScene;
    
    /**
     * GamePanel, constructor of class.
     */
    public GamePanel(){
    	instance  = this;
    	
		System.out.println("Main - main - Application Version");
		System.out.println("Main - main - width screen:" + StaticContent.widthScreen+" height screen: "+StaticContent.heightScreen);
    }
    
    
	/** 
	 * Start and run main thread.
	 */
    public void start() {
		/* Set conditional main thread to true */
		running = true;
        
        /* Init opengl */
        RenderGL.initOpenGL(width, height);
        
		/* Init, opening scene */
        currentScene = new SplashScene(this);
//        currentScene = new GamePlayScene(this);
        
        /* Init, language to using */
        Language.loadLanguage(Language.PORTUGUES);
        
        /* Game loop, update and render scene, calc difTime and fps */
        while (!Display.isCloseRequested() && running) {
            currentScene.keyboardInput();
            currentScene.mouseInput();
            
            currentScene.update(difTime);
            currentScene.render();
            currentScene.renderInterface();

			if (currentTime > 0) {
				difTime = (int) (System.currentTimeMillis() - currentTime);
			}
			
			currentTime = System.currentTimeMillis();
			if (((int) (currentTime / 1000f)) != lastTime) {
				FPS = sfps;
				sfps = 0;
			}
			
			sfps++;
			lastTime = (int) (currentTime / 1000f);
			
            Display.update();
            /* Limit fps */ 
            Display.sync(StaticContent.FPS_LIMIT);
        }

        Display.destroy();
    }
    
    /**
     * Set the display mode to be used. Fullscreen or windows.
     * 
     * @param width The width of the display required
     * @param height The height of the display required
     * @param fullscreen True if we want fullscreen mode
     */
    public void setDisplayMode(int width, int height, boolean fullscreen) {

        /* return if requested DisplayMode is already set */
        if ((Display.getDisplayMode().getWidth() == width) && 
            (Display.getDisplayMode().getHeight() == height) && 
            (Display.isFullscreen() == fullscreen)) {
                return;
        }

        try {
            DisplayMode targetDisplayMode = null;

            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                int freq = 0;

                for (int i=0;i<modes.length;i++) {
                    DisplayMode current = modes[i];

                    if ((current.getWidth() == width) && (current.getHeight() == height)) {
                        if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                            if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                                targetDisplayMode = current;
                                freq = targetDisplayMode.getFrequency();
                            }
                        }

                        /* 
                         * If we've found a match for bpp and frequence against the 
                         * original display mode then it's probably best to go for this one
                         * since it's most likely compatible with the monitor 
                         */
                        if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                            (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                                targetDisplayMode = current;
                                break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width,height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);

        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
        }
    }
}