package brickbreaker.powerup;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import brickbreaker.model.Brick;
import brickbreaker.model.Item;

public class Missile extends Item {
	private boolean visible;
	private Image state0;

	public Missile(Integer x, Integer y, Integer w, Integer h) {
		super(x, y, w, h, 1);
		state0 = new ImageIcon(this.getClass().getResource("/res/images/powerups/missileshots.png")).getImage(); 
		this.setVelY(5);
		visible = true;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
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
		
		if (y < 50) {
			visible = false;
		}
		
		 if (x_+w_ >= x && x_ <= x+w && y_+h_ >= y && y_ <= y+h){
			 ((Brick)i).dealDamage();
			 visible = false;
		 }
	}

	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		gfx.drawImage(state0, x, y, w, h, null);
		
	}

	@Override
	public void update() {
		this.getPos().setPosY(this.getPos().getPosY() - getVelY());
	}

}
