package objects;

import graphics.Sprite;
import managers.texture.TextureManager;
import objects.buildings.AmmunitionStorage;
import objects.buildings.Camp;
import objects.buildings.Refinery;

public class Player extends Sprite{

	public int[] cards;

	public Camp camp;
	public AmmunitionStorage ammunitionStorage;
	public Refinery refinery;
	
	public Player(float x, float y, int[] cards){
		super(null, x, y, 0, 0, false);
		this.cards = cards;
		
		camp = new Camp(TextureManager.camp, x, y, TextureManager.camp.width, TextureManager.camp.height, true);
		ammunitionStorage = new AmmunitionStorage(TextureManager.ammunitionStorage, x, y, TextureManager.ammunitionStorage.width, TextureManager.ammunitionStorage.height, true);
		refinery = new Refinery(TextureManager.refinery, x, y, TextureManager.refinery.width, TextureManager.refinery.height, true);
	}

	@Override
	public void update(int difTime) {
		
	}

	@Override
	public void render() {
		
	}
}
