package managers.texture;

/**
* GTexture, Graphic texture.
* 
* @author Gilvanei Gregorio
* @version 1.0
*/
public class GTexture {

    /** Texture OpenGL ID */
    public int id; 

    /** Image loaded width in pixels */
    public int width;
    /** Image loaded height in pixels */
    public int height;
    
    /** Proportion in axis X of image, image need not be square*/
    public float proportionX;
    /** Proportion in axis Y of image, image need not be square */
    public float proportionY;
}