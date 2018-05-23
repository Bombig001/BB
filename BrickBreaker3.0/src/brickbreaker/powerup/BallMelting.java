package brickbreaker.powerup;

import java.awt.Image;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import brickbreaker.model.Ball;
import brickbreaker.model.Item;

public class BallMelting extends PowerUp{

	public BallMelting(Integer x, Integer y, Item itemToEffect) {
		super(x, y, itemToEffect);
		this.setEffectDuration(6);
		Image img = new ImageIcon(this.getClass().getResource("/res/images/powerups/meltingball.png")).getImage();
		this.setImg(img);
		this.setEffect(new Callable<Boolean>() {
			public Boolean call() {
		        return ((Ball) getItemToEffect()).isEffectMelting();
			}
		});
	}
	
	@Override
	public void startEffect() {
		((Ball) this.getItemToEffect()).setEffectMelting(true);
	}

	@Override
	public void stopEffect() {
		((Ball) this.getItemToEffect()).setEffectMelting(false);
	}

}
