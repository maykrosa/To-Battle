package managers.texture;

import java.io.IOException;
import java.io.InputStream;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
* TextureManager, manager all textures load in game Acess static to each texture.
* 
* @author Gustavo Gregorio
* 
* @version 1.0
*/
public class TextureManager {
    
	/* Splash Scene */
    public static GTexture splashScreen;
    
    /* Menu Scene */
    public static GTexture menuScreen;
    public static GTexture flagPortuguese;
    public static GTexture flagEnglish;
    public static GTexture sfxOn;
    public static GTexture sfxOff;
    public static GTexture soundTrackOn;
    public static GTexture soundTrackOff;
    
	/* Select Stage Scene */
    public static GTexture selectStage;
    
	/* Draft Scene */
    public static GTexture draftScreen;
    public static GTexture[] cards;
    public static GTexture panelCardInformation;
    
    /* GamePlay Scene */
    public static GTexture player;
    public static GTexture mapa;
    
    /**
     * Load all GTextures of game. 
     */
    static{
    	/* Splash Scene */
        splashScreen = new GTexture();

        load(splashScreen, "PNG", "/splash_screen.png"); 
        
        /* Menu Scene */
        menuScreen = new GTexture();
        flagPortuguese = new GTexture();
        flagEnglish = new GTexture();
        sfxOn = new GTexture();
        sfxOff = new GTexture();
        soundTrackOn = new GTexture();
        soundTrackOff = new GTexture();
        
        load(menuScreen, "PNG", "/main_menu.png"); 
        load(flagPortuguese, "PNG", "/ui/portugues_flag.png"); 
        load(flagEnglish, "PNG", "/ui/english_flag.png"); 
        load(sfxOn, "PNG", "/ui/sfx_on.png"); 
        load(sfxOff, "PNG", "/ui/sfx_off.png"); 
        load(soundTrackOn, "PNG", "/ui/sound_track_on.png"); 
        load(soundTrackOff, "PNG", "/ui/sound_track_off.png"); 
        
    	/* Select Stage Scene */
        selectStage = new GTexture();

        load(selectStage, "PNG", "/select_screen.png"); 
        
    	/* Draft Scene */
        draftScreen = new GTexture();
        panelCardInformation = new GTexture();
        cards = new GTexture[24];
        for(int i=0; i<24; i++){
        	cards[i] = new GTexture();
        	
        	load(cards[i], "PNG", "/cards/card"+i+".png"); 
        }

        load(draftScreen, "PNG", "/draft_menu.png"); 
        load(panelCardInformation, "PNG", "/ui/panel_card_information.png");
        
        /* GamePlay Scene */
        player = new GTexture();
        mapa = new GTexture();
        
        load(player, "PNG", "/SpriteRange.png");
        load(mapa, "PNG", "/mapaBase.png");
    }
    
    /**
     * load, load a texture of your path file and put in GTexture send to reference.
     * 
     * @param GTexture dest, reference of GTexture
     * @param String extension, extension of file
     * @param String pathFile
     * 
     * @return boolean, result of operation
     */
    public static boolean load(GTexture dest, String extension, String pathFile){
        InputStream inputStream = TextureLoader.class.getClass().getResourceAsStream(pathFile);
        
        try{
            Texture tempTexture = TextureLoader.getTexture(extension, inputStream);
            
            dest.id = tempTexture.getTextureID();
            dest.width = tempTexture.getImageWidth();
            dest.height = tempTexture.getImageHeight();
            
            dest.proportionX = tempTexture.getWidth();
            dest.proportionY = tempTexture.getHeight();
            
            return true;
        }catch(IOException ex){
            System.out.println("TextureManager - load - Error load texture "+pathFile);
            
            return false;
        }
    }
    
}
