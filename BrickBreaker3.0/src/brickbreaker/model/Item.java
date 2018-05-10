package brickbreaker.model;

import brickbreaker.position.Position;

public abstract class Item implements ItemHandler{
	private Position pos;
	private Integer health;
	private Integer velX;
	private Integer velY;
	
	public Item (Integer x, Integer y, Integer w, Integer h, Integer health) {
		pos = new Position(x,y,w,h);
		this.health = health;
		velX = 0;
		velY = 0;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Integer x, Integer y) {
		pos.setPosX(x);
		pos.setPosY(y);
	}

	public Integer getHealth() {
		return health;
	}
	
	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getVelX() {
		return velX;
	}

	public void setVelX(Integer velX) {
		this.velX = velX;
	}

	public Integer getVelY() {
		return velY;
	}

	public void setVelY(Integer velY) {
		this.velY = velY;
	}
}
