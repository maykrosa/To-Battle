package graphics;

public class AnimatedSprite {
	
	public int numberAnimations;
	public int numberFrames;
	public int currentAnimation;
	public int currentFrame;
	public int timeBetweenFrame;
	public int timerAnimation;
	
	public AnimatedSprite(int numberAnimations, int numberFrames, int timeBetweenFrame){
		this.numberAnimations = numberAnimations;
		this.numberFrames = numberFrames;
		this.currentAnimation = 0;
		this.currentFrame = 0;
		this.timeBetweenFrame = timeBetweenFrame;
		timerAnimation = 0;
	}
	
	public void update(int difTime){
		timerAnimation += difTime;
		
		if(timerAnimation >= timeBetweenFrame){
			timerAnimation -= timeBetweenFrame;
			
			currentFrame++;
			if(currentFrame >= numberFrames)
				currentFrame = 0;
		}
	}
}
