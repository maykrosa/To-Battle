package graphics;

import graphics.Sprite;
import managers.texture.GTexture;
import utils.RenderGL;

public class RectangleGL extends Sprite {
	private float alpha = 0;
	private GTexture texture = null;

	public RectangleGL(GTexture texture, float x, float y, float width,
			float height, float alpha) {
		super(texture, x, y, width, height, false);
		this.alpha = alpha;
		this.width = width;
		this.height = height;
		this.texture = texture;
	}

	
	
	public GTexture getTexture() {
		return texture;
	}
	public void setTexture(GTexture texture) {
		this.texture = texture;
	}



	@Override
	public void update(int difTime) {

	}

	@Override
	public void render() {
		RenderGL.drawRectWithTexture(texture, position.x, position.y, width,
				height, alpha);

	}

	public boolean contains(float x, float y) {
		if (x > position.x && x < width && y > position.y && y < height)
			return true;
		return false;
	}
}
