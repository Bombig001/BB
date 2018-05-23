package brickbreaker.powerup;

import java.awt.Graphics;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

import brickbreaker.controller.GameController;
import brickbreaker.model.Item;

public abstract class PowerUp extends Item {
	private Image img;
	private boolean visible;
	private boolean collected;
	private boolean dead;
	private int effectDuration;
	private Item itemToEffect;
	private Callable<Boolean> effect;
	private Instant timeStart;
	private Instant timeStop;
	private Duration timePastBetween;
	
	public PowerUp(Integer x, Integer y, Item itemToEffect) {
		super(x, y, 50, 13);
		this.setVelY(2);
		visible = true;
		collected = false;
		effectDuration = 0;
		this.itemToEffect = itemToEffect;
	}
	
	@Override
	public void colission(Item i) {
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
				try {
					if (!effect.call()) {
						this.setCollected(true);
					} else {
						this.setDead(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (y > GameController.height) {
			if (this.isVisible()) {
				this.setVisible(false);
				this.setDead(true);
			}
		}
	}
	
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		if (this.isVisible()) {
			gfx.drawImage(img, x, y, w, h,null);
		}
		
	}
	
	@Override
	public void update() {
		if(isVisible()) {
			this.getPos().setPosY(this.getPos().getPosY() + getVelY());
		}
		
		try {
			if (!effect.call() && this.isCollected()) {
				this.setTimeStart(Instant.now());
				startEffect();
			}else if (this.getTimeStart() != null && this.isCollected() && this.checkIfExpired()) {
				stopEffect();
				this.setCollected(false);
				this.setDead(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getEffectDuration() {
		return effectDuration;
	}

	public void setEffectDuration(int effectDuration) {
		this.effectDuration = effectDuration;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Item getItemToEffect() {
		return itemToEffect;
	}

	public void setTimeStart(Instant timeStart) {
		this.timeStart = timeStart;
	}
	
	public Instant getTimeStart() {
		return timeStart;
	}

	public Duration getTimePastBetween() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		return timePastBetween;
	}

	public abstract void startEffect();
	      
	public abstract void stopEffect();
	
	public boolean checkIfExpired() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		if (timePastBetween.getSeconds() >= effectDuration) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	public Image getImg() {
		return img;
	}

	public void setEffect(Callable<Boolean> effect) {
		this.effect = effect;
	}
}
