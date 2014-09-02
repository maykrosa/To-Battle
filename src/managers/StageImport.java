package managers;

import game.GamePanel;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import objects.Field;
import objects.Path;
import objects.Player;

import utils.PVector;

public class StageImport {
	
	public static String name;
	public static int width;
	public static int height;

	public static LinkedList<Field> fields;
	public static LinkedList<Path> paths;
	
	private static LinkedList<Army> armys;
	
	public static void loadStageFile(String filename){
        String line;

        name = "";
        armys = new LinkedList<>();
        fields = new LinkedList<>();
        paths = new LinkedList<>();
        
        try{
        	InputStream is = GamePanel.instance.getClass().getResourceAsStream("/"+filename);
        	InputStreamReader isr = new InputStreamReader(is);
        	BufferedReader bufferedReader = new BufferedReader(isr);

        	loadHeader(bufferedReader);
            while((line=bufferedReader.readLine())!=null){
            	if(line.contains("Army")){
            		loadArmy(bufferedReader);
            		continue;
            	}
            	
            	if(line.contains("Field")){
            		loadField(bufferedReader);
            		continue;
            	}
            	
            	if(line.contains("Paths")){
            		loadPaths(bufferedReader);
            		continue;
            	}
            }
            
            bufferedReader.close();
            is.close();
            isr.close();
            
            System.out.println("Stage import - loadStageFile - sucefull");
        }catch(IOException e){
        	System.out.println("Stage import - loadStageFile - error");
        }
    }

	public static void fillPlayer(Player player, int id){
		Army a = armys.get(id); 
		
		player.camp.position = new PVector(a.campPostion.x, a.campPostion.y);
		player.camp.width = a.campPostion.width;
		player.camp.height = a.campPostion.height;
		
		player.ammunitionStorage.position = new PVector(a.ammunitionPosition.x, a.ammunitionPosition.y);
		player.ammunitionStorage.width = a.ammunitionPosition.width;
		player.ammunitionStorage.height = a.ammunitionPosition.height;
		
		player.refinery.position = new PVector(a.refineryPosition.x, a.refineryPosition.y);
		player.refinery.width = a.refineryPosition.width;
		player.refinery.height = a.refineryPosition.height;
		
		player.researchCenter.position = new PVector(a.reseachPosition.x, a.reseachPosition.y);
		player.researchCenter.width = a.reseachPosition.width;
		player.researchCenter.height = a.reseachPosition.height;
	}
	
	public static int getNumberArmy(){
		return armys.size();
	}
	
	private static void loadHeader(BufferedReader bufferedReader) throws IOException{
        String line;
        String[] subLine;
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        name = subLine[1];
 
        line = bufferedReader.readLine();
        subLine = line.split(":");
        width = Integer.parseInt(subLine[1]);
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        height = Integer.parseInt(subLine[1]);
	}
	
	private static void loadArmy(BufferedReader bufferedReader) throws IOException{
        String line;
        String[] subLine;
        String[] subSubLine;
        
        Army a = new Army();
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        subSubLine = subLine[1].split(",");
		a.campPostion = new Rectangle(Integer.parseInt(subSubLine[0]), Integer.parseInt(subSubLine[1]),
        		Integer.parseInt(subSubLine[2]), Integer.parseInt(subSubLine[3]));
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        subSubLine = subLine[1].split(",");
        a.ammunitionPosition = new Rectangle(Integer.parseInt(subSubLine[0]), Integer.parseInt(subSubLine[1]),
        		Integer.parseInt(subSubLine[2]), Integer.parseInt(subSubLine[3]));
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        subSubLine = subLine[1].split(",");
        a.refineryPosition = new Rectangle(Integer.parseInt(subSubLine[0]), Integer.parseInt(subSubLine[1]),
        		Integer.parseInt(subSubLine[2]), Integer.parseInt(subSubLine[3]));
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        subSubLine = subLine[1].split(",");
        a.reseachPosition = new Rectangle(Integer.parseInt(subSubLine[0]), Integer.parseInt(subSubLine[1]),
        		Integer.parseInt(subSubLine[2]), Integer.parseInt(subSubLine[3]));
        
        armys.add(a);
	}

	private static void loadField(BufferedReader bufferedReader) throws IOException{
        String line;
        String[] subLine;
        String[] subSubLine;
        
        Field f = new Field();
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        f.resource = Integer.parseInt(subLine[1]);
       
        line = bufferedReader.readLine();
        subLine = line.split(":");
        subSubLine = subLine[1].split(",");
        f.position = new Rectangle(Integer.parseInt(subSubLine[0]), Integer.parseInt(subSubLine[1]),
        		Integer.parseInt(subSubLine[2]), Integer.parseInt(subSubLine[3]));

        fields.add(f);
	}
	
	private static void loadPaths(BufferedReader bufferedReader) throws IOException{
        String line;
        String[] subLine;
        String[] subSubLine;
        
        Path p = new Path();
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        p.source = Integer.parseInt(subLine[1]);
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        p.numberWaypoints = Integer.parseInt(subLine[1]);
        
        line = bufferedReader.readLine();
        subLine = line.split(":");
        p.source = Integer.parseInt(subLine[1]);
       
        p.waypoints = new LinkedList<>();
        for(int i=0; i<p.numberWaypoints; i++){
	        line = bufferedReader.readLine();
	        subLine = line.split(":");
	        subSubLine = subLine[1].split(",");
	        p.waypoints.add(new PVector(Integer.parseInt(subSubLine[0]), Integer.parseInt(subSubLine[1])));
        }
        paths.add(p);
	}

	static class Army{
		Rectangle campPostion;
		Rectangle ammunitionPosition;
		Rectangle refineryPosition;
		Rectangle reseachPosition;
	}
}