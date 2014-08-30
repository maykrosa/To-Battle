package managers;

import game.GamePanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
* Language, constansts with languages available, and yours strings.
* 
* @author Gilvanei Gregorio
* @version 1.0
*/
public class Language {
    
	/** Constant, english language */
    public final static String ENGLISH = "english";
    /** Constant, portuguese language */
    public final static String PORTUGUES = "portugues";
    
    /** List with all text load from file and avaliable to use */
    private static ArrayList<String> texts;
    /** Current language load in game */
    private static String currentLanguage;
    
    /** 
     * loadLanguage, using language string load in memory strings 
     * of language file.
     * 
     *  @param String language
     */
    public static void loadLanguage(String language){
    	texts = new ArrayList<String>();
    	
        try{
        	InputStream is = GamePanel.instance.getClass().getResourceAsStream("/language/"+language+".txt");
        	InputStreamReader isr = new InputStreamReader(is);
        	BufferedReader bufferedReader = new BufferedReader(isr);
        	
        	String line;
        	
            while((line = bufferedReader.readLine())!=null){
                /* Discards empty lines */
                if (line.isEmpty())
                    continue;

                /* Discart comments */
                if(line.contains("#"))
                    continue;

                String[] subString = line.split("=");

            	/* Saving the Strings */
                texts.add(subString[1]);
            }
            
            bufferedReader.close();
            isr.close();
            is.close();
            
            currentLanguage = language;
        }catch(IOException e){
        	System.out.println("Language - loadLanguage - Error load language "+language);
        }
    }
    
    /** 
     * getText, get string of list of string of language, using index.
     * 
     *  @param int index
     */
    public static String getText(int index){
    	return texts.get(index);
    }
    
    /** 
     * getCurrentLanguage, return string with current language load in game.
     * Used to see which language is being used.
     */
    public static String getCurrentLanguage(){
    	return currentLanguage;
    }
}
