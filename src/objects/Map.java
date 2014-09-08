package objects;

import graphics.Sprite;
import java.util.LinkedList;
import managers.texture.GTexture;
import utils.PVector;
import utils.RenderGL;

public class Map extends Sprite{

	public String name;
	
	public LinkedList<Field> fields;
	public LinkedList<Path> paths;
	
	//TODO Remove late
	public static boolean debug = false;
	
    public Map(GTexture texture, float x, float y, float width, float height, boolean sort, String name) {
        super(texture, x, y, width, height, sort);

        this.name = name;
    }

    @Override
    public void update(int difTime){
    	
    }
    
    @Override
    public void render(){
    	RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1f);
    	
    	if(debug){
	    	for(Field f : fields){
	    		RenderGL.drawRectWithColor(new PVector(1, 0, 0), (int)f.position.x, (int)f.position.y, (int)(f.position.x+f.position.width),(int)(f.position.y + f.position.height),0.5f);
	    	}
    	}

//    	for(PVector wp : paths.get(13).waypoints){
//    		RenderGL.drawRectWithColor(new PVector(1, 1, 0), (int)wp.x, (int)wp.y, (int)(wp.x+50),(int)(wp.y + 50),0.5f);
//    	}
    }
}
