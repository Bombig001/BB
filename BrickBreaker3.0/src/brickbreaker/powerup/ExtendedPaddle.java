package brickbreaker.powerup;

import java.awt.Graphics;
import java.time.Instant;

import brickbreaker.model.Paddle;
import brickbreaker.controller.GameController;
import brickbreaker.model.Item;

public class ExtendedPaddle extends PowerUp{
	private Item itemToEffect;

	public ExtendedPaddle(Integer x, Integer y, Integer w, Integer h, Item itemToEffect) {
		super(x, y, w, h);
		this.setCoolDown(3);
		this.itemToEffect = itemToEffect;
	}

	@Override
	public boolean colission(Item i) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		int x_ = i.getPos().getPosX().intValue();
		int y_ = i.getPos().getPosY().intValue();
		int w_ = i.getPos().getWidth().intValue();
		int h_ = i.getPos().getHeight().intValue();
		
		if (x_+w_ >= x && x_ <= x+w && y_+h_ >= y && y_ <= y+h){
			if (this.isVisible()) {
				this.setVisible(false);
				if (!((Paddle) itemToEffect).isExtendedEffect()) {
					this.setCollected(true);
				} else {
					this.setDead(true);
				}
			}
		} else if (y > GameController.windowHeight) {
			if (this.isVisible()) {
				this.setVisible(false);
				this.setDead(true);
			}
		}
		
		return false;
	}

	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		if (this.isVisible()) {
			//gfx.fillRect(x, y, w, h);
			gfx.drawImage(this.getImg(), x, y, w, h,null);
		}
		
	}

	@Override
	public void update() {
		if(isVisible()) {
			this.getPos().setPosY(this.getPos().getPosY() + getVelY());
		}
		if (!((Paddle) itemToEffect).isExtendedEffect() && this.isCollected()) {
			((Paddle) itemToEffect).setExtendedEffect(true);
			this.setTimeStart(Instant.now());
			startEffect();
		}else if (this.getTimeStart() != null && this.isCollected() && this.checkIfExpired()) {
			stopEffect();
			((Paddle) itemToEffect).setExtendedEffect(false);
			this.setCollected(false);
		}
		
	}

	@Override
	public void startEffect() {
		itemToEffect.getPos().setPosX(itemToEffect.getPos().getPosX()-10);	
		itemToEffect.getPos().setWidth(itemToEffect.getPos().getWidth()+20);
	}

	@Override
	public void stopEffect() {
		itemToEffect.getPos().setPosX(itemToEffect.getPos().getPosX()+10);	
		itemToEffect.getPos().setWidth(itemToEffect.getPos().getWidth()-20);
		this.setDead(true);
	}
	
}
