package brickbreaker.position;

import java.util.Random;

public class Strategy {
	private Random rnd = new Random();
	private int chance;
	
	public boolean getRandomChance() {
		// 100 god like
		// 80 sehr schwer
		// 70 mittel-schwer
		// 67 leicht
		if (rnd.nextInt(100) >= chance) {
			return true;
		}
		return false;
	}
	
	public Strategy(int chance) {
		this.chance = chance;
	}
}
