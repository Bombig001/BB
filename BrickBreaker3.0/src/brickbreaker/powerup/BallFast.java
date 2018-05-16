package brickbreaker.powerup;

import java.awt.Image;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import brickbreaker.model.Ball;
import brickbreaker.model.Item;

public class BallFast extends PowerUp{
	private int speedValue;
	
	public BallFast(Integer x, Integer y, Item itemToEffect) {
		super(x, y, itemToEffect);
		speedValue = 9;
		this.setCoolDown(3);
		Image img = new ImageIcon(this.getClass().getResource("/res/images/powerups/fastball.png")).getImage();
		this.setImg(img);
		this.setEffect(new Callable<Boolean>() {
			public Boolean call() {
		        return ((Ball) getItemToEffect()).isEffectFast();
			}
		});
	}

	@Override
	public void startEffect() {
		((Ball) getItemToEffect()).setEffectFast(true);
			((Ball) this.getItemToEffect()).setSpeed(speedValue);
	}

	@Override
	public void stopEffect() {
		((Ball) getItemToEffect()).setEffectFast(false);
		((Ball) this.getItemToEffect()).setSpeed(((Ball) this.getItemToEffect()).getDefSpeed());
		this.setDead(true);
		this.setCollected(false);
	}
}
