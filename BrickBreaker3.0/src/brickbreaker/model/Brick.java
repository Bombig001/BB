package brickbreaker.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import brickbreaker.sound.Sound;

public class Brick extends Item {
	
	private boolean isSmashed;
	private Image state0;
	private Image state1;
	private Image state2;
	private static Sound brickToBallSound0;
	private static Sound brickToBallSound1;

	public Brick(Integer x, Integer y, Integer w, Integer h, Integer color) {
		super(x, y, w, h, 1);
		if ( color == 1) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/blau1.png")).getImage();
		} else if ( color == 2) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/rot1.png")).getImage();
		} else if (color == 3) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/yellow1.png")).getImage();
		} else if ( color == 4) {
			this.setHealth(3);
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey0.png")).getImage();
			state1 = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey1.png")).getImage();
			state2 = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey2.png")).getImage();
		}
		
		brickToBallSound0 = new Sound("/res/sounds/bounceBrick0.wav",-10.0f);
		brickToBallSound1 = new Sound("/res/sounds/bounceBrick1.wav",-10.0f);
	}
	
	public void dealDamage() {
		if(this.getHealth() > 1) {
			this.setHealth(this.getHealth().intValue() - 1);
		    brickToBallSound1.start();
		} else {
			this.isSmashed = true;
			brickToBallSound0.start();
		}
	}

	public boolean getIsSmashed() {
		return isSmashed;
	}

	public void setSmashed(boolean isSmashed) {
		this.isSmashed = isSmashed;
	}
	
	public static Sound getBrickToBallSound0() {
		return brickToBallSound0;
	}

	public static Sound getBrickToBallSound1() {
		return brickToBallSound1;
	}

	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		
		//gfx.fillRect(x, y, w, h);
		if (this.getHealth() == 1) {
			gfx.drawImage(state0, x, y, w, h, null);
		} else if (this.getHealth() == 2) {
			gfx.drawImage(state1, x, y, w, h, null);
		} else if (this.getHealth() == 3) {
			gfx.drawImage(state2, x, y, w, h, null);
		}
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
			    if (y_ >= y) {
			    	i.setVelY(((Ball) i).getSpeed());
			    }else{
			    	i.setVelY(-((Ball) i).getSpeed());
			    }
			    if(x_+w_ <= x+w/4) {
			    	i.setVelX(-((Ball) i).getSpeed());
			    } else if(x_ >= x+w-(w/4)) {
			    	i.setVelX(((Ball) i).getSpeed());
			    }
			    return true;
			  }
		return false;
	}

	@Override
	public void update() {
		
	}
}
