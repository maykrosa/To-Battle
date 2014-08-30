package objects;

import graphics.Sprite;
import managers.texture.GTexture;
import utils.RenderGL;

public class Map extends Sprite{

    public Map(GTexture texture, float x, float y, float width, float height, boolean sort) {
        super(texture, x, y, width, height, sort);

    }

    @Override
    public void update(int difTime){
    	
    }
    
    @Override
    public void render(){
    	RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 1f);
    }
}
