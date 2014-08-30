package objects;

import utils.RenderGL;
import graphics.AnimatedSprite;
import graphics.Sprite;
import managers.texture.GTexture;

public class Unit extends Sprite{
    
    public Unit(GTexture texture, float x, float y, float width, float height, boolean sort, AnimatedSprite animated) {
        super(texture, x, y, width, height, sort, animated);

    }

    @Override
    public void update(int difTime){
    	animated.update(difTime);
    }
    
    @Override
    public void render(){
    	RenderGL.drawRectWithTexture(texture, position.x, position.y, position.x+width, position.y+height, 
    			texture.width*((float)animated.currentFrame/(float)animated.numberFrames), texture.height*((float)animated.currentAnimation/(float)animated.numberAnimations), 
    			texture.width*((float)(animated.currentFrame+1)/(float)animated.numberFrames), texture.height*((float)(animated.currentAnimation+1)/(float)animated.numberAnimations), 1);
    }
}