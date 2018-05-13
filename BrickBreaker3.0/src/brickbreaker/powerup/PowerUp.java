package brickbreaker.powerup;

import java.awt.Image;
import java.awt.Toolkit;
import java.time.Duration;
import java.time.Instant;

import javax.swing.ImageIcon;

import brickbreaker.model.ItemHandler;
import brickbreaker.position.Position;

public abstract class PowerUp implements ItemHandler{
	private Position pos;
	private int velY;
	private Image img;
	private boolean visible;
	private boolean collected;
	private boolean dead;
	private int coolDown;
	private Instant timeStart;
	private Instant timeStop;
	private Duration timePastBetween;
	
	public PowerUp(Integer x, Integer y, Integer w, Integer h) {
		pos = new Position(x, y, w, h);
		velY = 3;
		visible = true;
		collected = false;
		img = new ImageIcon(this.getClass().getResource("/res/images/powerups/enlarge.png")).getImage(); 
		coolDown = 0;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
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

	public void setTimeStart(Instant timeStart) {
		this.timeStart = timeStart;
	}
	
	public Instant getTimeStart() {
		return timeStart;
	}

	public abstract void startEffect();
	      
	public abstract void stopEffect();
	
	public boolean checkIfExpired() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		if (timePastBetween.getSeconds() >= coolDown) {
			return true;
		} else {
			return false;
		}
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
}
