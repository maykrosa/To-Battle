package graphics;

import org.lwjgl.util.Rectangle;

import utils.PVector;
import managers.texture.GTexture;

public abstract class Sprite {
    
    public GTexture texture;
    
    /* Geometric group */
    // In pixels
    public PVector position;
    public float width;
    public float height;
    
    public Rectangle collisionBox;
    
    /* */
    public boolean active;
    
    /* Sort, maps dont sort */
    public boolean sort;
    
    protected AnimatedSprite animated;
    
    public Sprite(GTexture texture, float x, float y, float width, float height, boolean sort){
        this.texture = texture;
        
        /* Geometric group */
        this.position = new PVector(x, y);
        this.width = width;
        this.height = height;
        
        this.collisionBox = new Rectangle((int)x, (int)y, (int)width, (int)height);
        
        this.active = true;
        this.sort = sort;
        
        this.animated = null;
    }
    
    public Sprite(GTexture texture, float x, float y, float width, float height, boolean sort, AnimatedSprite animated){
        this.texture = texture;
        
        /* Geometric group */
        this.position = new PVector(x, y);
        this.width = width;
        this.height = height;
        
        this.collisionBox = new Rectangle((int)x, (int)y, (int)width, (int)height);
        
        this.active = true;
        this.sort = sort;
        
        this.animated = animated;
    }
    
    public abstract void update(int difTime);
    public abstract void render();
}