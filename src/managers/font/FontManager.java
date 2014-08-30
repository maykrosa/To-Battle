package managers.font;

import java.awt.Font;
import java.io.InputStream;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

/**
* FontManager, manager all fonts load in game Acess static to each font.
* 
* @author Gustavo Gregorio
* 
* @version 1.0
*/
@SuppressWarnings("deprecation")
public class FontManager {

	/** The fonts to draw to the screen */
	public static TrueTypeFont font;
	public static TrueTypeFont font2;
	
    /**
     * Load all fonts of game. 
     */
    static{
    	font = loadFromJava("Times New Roman", 24);
    	font2 = loadFromFile("ITCBLKAD.ttf", 24f);
    }
    
    /**
     * loadFromFile, load font of your path file and return a TrueTypeFont.
     * 
     * @param String filePath
     * @param float size
     * 
     * @return TrueTypeFont
     */
    public static TrueTypeFont loadFromFile(String filePath, float size){
    	InputStream inputStream = ResourceLoader.getResourceAsStream(filePath);
    	Font awtFont = null;
    	
		try {
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			 /* set font size */
	    	awtFont = awtFont.deriveFont(size);
	    	
	    	return new TrueTypeFont(awtFont, true);
		}catch(Exception e){
            System.out.println("FontManager - loadFromFile - Error load font "+filePath);
            
			return null;
		}
    }
    
    /**
     * loadFromJava, load font from java using your fontName and return a TrueTypeFont.
     * 
     * @param String filePath
     * @param float size
     * 
     * @return TrueTypeFont
     */
    public static TrueTypeFont loadFromJava(String fontName, int size){
    	Font awtFont = new Font(fontName, Font.BOLD, size);
    	
    	return new TrueTypeFont(awtFont, true);
    }
}
