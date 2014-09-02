package objects;

import graphics.Sprite;
import managers.texture.TextureManager;
import objects.buildings.AmmunitionStorage;
import objects.buildings.Camp;
import objects.buildings.Refinery;
import objects.buildings.ResearchCenter;

public class Player extends Sprite{

	public Camp camp;
	public AmmunitionStorage ammunitionStorage;
	public Refinery refinery;
	public ResearchCenter researchCenter;
	
	public Player( int[] cards){
		super(null,0, 0, 0, 0, false);
		
		camp = new Camp(TextureManager.camp, true, null);
		ammunitionStorage = new AmmunitionStorage(TextureManager.ammunitionStorage, true);
		refinery = new Refinery(TextureManager.refinery, true);
		researchCenter = new ResearchCenter(TextureManager.researchCenter,  true);
	}

	@Override
	public void update(int difTime) {
		
	}

	@Override
	public void render() {
		
	}
}
