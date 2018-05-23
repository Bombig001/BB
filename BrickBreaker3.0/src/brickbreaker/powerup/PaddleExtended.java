package brickbreaker.powerup;

import java.awt.Image;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import brickbreaker.model.Paddle;
import brickbreaker.model.Item;

public class PaddleExtended extends PowerUp{
	private int extendValue;

	public PaddleExtended(Integer x, Integer y, Item itemToEffect) {
		super(x, y, itemToEffect);
		extendValue = 10;
		this.setEffectDuration(3);
		Image img = new ImageIcon(this.getClass().getResource("/res/images/powerups/extendpaddle.png")).getImage();
		this.setImg(img);
		this.setEffect(new Callable<Boolean>() {
			public Boolean call() {
		        return ((Paddle) getItemToEffect()).isEffectExtended();
			}
		});
	}

	@Override
	public void startEffect() {
		((Paddle) this.getItemToEffect()).setEffectExtended(true);
		this.getItemToEffect().getPos().setPosX(this.getItemToEffect().getPos().getPosX()-extendValue);	
		this.getItemToEffect().getPos().setWidth(this.getItemToEffect().getPos().getWidth()+(extendValue*2));
	}

	@Override
	public void stopEffect() {
		((Paddle) this.getItemToEffect()).setEffectExtended(false);
		this.getItemToEffect().getPos().setPosX(this.getItemToEffect().getPos().getPosX()+extendValue);	
		this.getItemToEffect().getPos().setWidth(this.getItemToEffect().getPos().getWidth()-(extendValue*2));
	}
	
}
