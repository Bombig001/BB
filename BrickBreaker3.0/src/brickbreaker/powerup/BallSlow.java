package brickbreaker.powerup;

import java.awt.Image;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import brickbreaker.model.Ball;
import brickbreaker.model.Item;

public class BallSlow extends PowerUp{
private int speedValue;
	
	public BallSlow(Integer x, Integer y, Item itemToEffect) {
		super(x, y, itemToEffect);
		speedValue = 5;
		this.setCoolDown(3);
		Image img = new ImageIcon(this.getClass().getResource("/res/images/powerups/slowball.png")).getImage();
		this.setImg(img);
		this.setEffect(new Callable<Boolean>() {
			public Boolean call() {
		        return ((Ball) getItemToEffect()).isEffectSlow();
			}
		});
	}

	@Override
	public void startEffect() {
		((Ball) getItemToEffect()).setEffectSlow(true);
			((Ball) this.getItemToEffect()).setSpeed(speedValue);
	}

	@Override
	public void stopEffect() {
		((Ball) getItemToEffect()).setEffectSlow(false);
		((Ball) this.getItemToEffect()).setSpeed(((Ball) this.getItemToEffect()).getDefSpeed());
		this.setDead(true);
	}
}
