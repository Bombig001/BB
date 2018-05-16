package brickbreaker.powerup;

import java.awt.Image;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import brickbreaker.model.Item;
import brickbreaker.model.Paddle;

public class PaddleShortened extends PowerUp{

	private int shortingValue;
	
	public PaddleShortened(Integer x, Integer y, Item itemToEffect) {
		super(x, y, itemToEffect);
		shortingValue = 10;
		this.setCoolDown(3);
		Image img = new ImageIcon(this.getClass().getResource("/res/images/powerups/shortpaddle.png")).getImage();
		this.setImg(img);
		this.setEffect(new Callable<Boolean>() {
			public Boolean call() {
		        return ((Paddle) getItemToEffect()).isEffectShortened();
			}
		});
	}

	@Override
	public void startEffect() {
		((Paddle) this.getItemToEffect()).setEffectShortened(true);
		this.getItemToEffect().getPos().setPosX(this.getItemToEffect().getPos().getPosX()+shortingValue);	
		this.getItemToEffect().getPos().setWidth(this.getItemToEffect().getPos().getWidth()-(shortingValue*2));
	}

	@Override
	public void stopEffect() {
		((Paddle) this.getItemToEffect()).setEffectShortened(false);
		this.getItemToEffect().getPos().setPosX(this.getItemToEffect().getPos().getPosX()-shortingValue);	
		this.getItemToEffect().getPos().setWidth(this.getItemToEffect().getPos().getWidth()+(shortingValue*2));
		this.setDead(true);
		this.setCollected(false);
	}

}
