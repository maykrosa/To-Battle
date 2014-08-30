package utils;

import managers.texture.GTexture;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
* RenderGL, class with static functions. OpenGL calls.
* 
* @author Gilvanei Gregorio
* @version 1.0
*/
public class RenderGL {

	/** 
	 * initOpenGL, init display and init funcitions that need game.
	 * 
	 * @param int width, width of viewport
	 * @param int height, height of viewport
	 */
	public static void initOpenGL(int width, int height){
		try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    
        /* Enable alpha blending */
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/** 
	 * TODO mudar o modo de renderização de modo imediato para vbo.
	 * drawRectWithTexture, render a rect with texture.
	 * 
	 * @param GTexture texture
	 * @param int x1
	 * @param int y1
	 * @param int x2
	 * @param int y2
	 * @param float alpha
	 */
	public static void drawRectWithTexture(GTexture texture, float x1, float y1, float x2, float y2, float alpha){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.id);
        
        GL11.glColor4f(1, 1, 1, alpha);
        
        GL11.glBegin(GL11.GL_QUADS);
            /* Texture is flipped in Y (Top to Bottom)*/
            /* Right-bottom */
            GL11.glTexCoord2f(texture.proportionX, 0);
            GL11.glVertex2f(x2, y1);

            /* Right-top */
            GL11.glTexCoord2f(texture.proportionX, texture.proportionY);
            GL11.glVertex2f(x2, y2);

            /* Left-top */
            GL11.glTexCoord2f(0, texture.proportionY);
            GL11.glVertex2f(x1, y2);

            /* Left-bottom */
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(x1, y1);
        GL11.glEnd();
        
        GL11.glColor4f(1, 1, 1, 1);
	}
	
	/** 
	 * TODO mudar o modo de renderização de modo imediato para vbo.
	 * drawRectWithTexture, render a rect with texture.
	 * 
	 * @param GTexture texture
	 * @param int x1Dest
	 * @param int y1Dest
	 * @param int x2Dest
	 * @param int y2Dest
	 * @param int x1Source
	 * @param int y1Source
	 * @param int x2Source
	 * @param int y2Source
	 * @param float alpha
	 */
	public static void drawRectWithTexture(GTexture texture, float x1Dest, float y1Dest, float x2Dest, float y2Dest, 
			float x1Source, float y1Source, float x2Source, float y2Source, float alpha){
		float x1Prop = x1Source/texture.width;
		float y1Prop = y1Source/texture.height;
		float x2Prop = x2Source/texture.width;
		float y2Prop = y2Source/texture.height;

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.id);
        
        GL11.glColor4f(1, 1, 1, alpha);
        
        GL11.glBegin(GL11.GL_QUADS);
            /* Texture is flipped in Y (Top to Bottom)*/
            /* Right-bottom */
        GL11.glTexCoord2f(texture.proportionX*x2Prop, texture.proportionY*y1Prop);
        GL11.glVertex2f(x2Dest, y1Dest);

        /* Right-top */
        GL11.glTexCoord2f(texture.proportionX*x2Prop, texture.proportionY*y2Prop);
        GL11.glVertex2f(x2Dest, y2Dest);

        /* Left-top */
        GL11.glTexCoord2f(texture.proportionX*x1Prop, texture.proportionY*y2Prop);
        GL11.glVertex2f(x1Dest, y2Dest);

        /* Left-bottom */
        GL11.glTexCoord2f(texture.proportionX*x1Prop, texture.proportionY*y1Prop);
        GL11.glVertex2f(x1Dest, y1Dest);
        GL11.glEnd();
        
        GL11.glColor4f(1, 1, 1, 1);
	}
	
	/** 
	 * TODO mudar o modo de renderização de modo imediato para vbo.
	 * drawRectWithColor, render a rect with color.
	 * 
	 * @param PVector color
	 * @param int x
	 * @param int y
	 * @param int width
	 * @param int height
	 * @param float color
	 */
	public static void drawRectWithColor(PVector color,float x1, float y1, float x2, float y2, float alpha){
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
        GL11.glColor4f(color.x, color.y, color.z, alpha);
        
        GL11.glBegin(GL11.GL_QUADS);
		    /* Texture is flipped in Y (Top to Bottom)*/
		    /* Right-bottom */
		    GL11.glVertex2f(x2, y1);
		
		    /* Right-top */
		    GL11.glVertex2f(x2, y2);
		
		    /* Left-top */
		    GL11.glVertex2f(x1, y2);
		
		    /* Left-bottom */
		    GL11.glVertex2f(x1, y1);
        GL11.glEnd();
        
        GL11.glColor4f(1, 1, 1, 1);
	}
}
