package objects;

import java.util.LinkedList;

import graphics.Sprite;
import managers.texture.GTexture;
import utils.RenderGL;

public class Map extends Sprite{

	public String name;
	
	public LinkedList<Field> fields;
	public LinkedList<Path> paths;
	
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
    }
}
