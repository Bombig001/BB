package brickbreaker.position;

import java.util.Random;

public class Strategy {
	private Random rnd = new Random();
	private int chance;
	
	public boolean getRandomChance() {
		// 100 god like
		// 90 sehr schwer
		// 80 mittel-schwer
		// 70 leicht
		if (rnd.nextInt(100) <= chance) {
			return true;
		}
		return false;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}
	
}
