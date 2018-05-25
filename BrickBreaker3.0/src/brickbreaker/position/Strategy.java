package brickbreaker.position;

import java.util.Random;

public class Strategy {
	private Random rnd = new Random();
	private int chance;
	
	public boolean getRandomChance() {
		
		if (rnd.nextInt(100) <= chance) {
			return true;
		}
		return false;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}
	
}
