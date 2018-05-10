package brickbreaker.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import brickbreaker.controller.GameController;
import brickbreaker.sound.Sound;

public class Ball extends Item {
	private int speed = 5;
	private Image state0;
	private static Sound ballSound;

	public Ball(Integer x, Integer y, Integer w, Integer h, int i) {
		super(x, y, w, h, 1);
		this.setVelX(-speed);
		this.setVelY(speed);
		//state0 = Toolkit.getDefaultToolkit().getImage("res/ball/ball0.png");
		state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball0.png")).getImage(); 
		ballSound = new Sound("/res/sounds/bounce.wav",-10.0f);
	}
	
	public void reset() {
		this.getPos().setPosX(200);
		this.getPos().setPosY(100);
	}
	
	public static Sound getBallSound() {
		return ballSound;
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
		
		if (x+speed <= 0) {
			this.setVelX(speed);
			ballSound.start();
		}
		
		if (x+w >= GameController.windowWidth) {
			this.setVelX(-speed);
			ballSound.start();
		}
		
		if (y-speed <= 50) {
			this.setVelY(speed);
			ballSound.start();
		}
		
		if (y+h >= GameController.windowHeight) {
			reset();
		}		
		return false;
	}
	
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		
		
		gfx.drawImage(state0, x, y, w, h, null);
		
		//gfx.fillOval(x, y, w, h);
		
	}
	
	@Override
	public void update() {
		this.getPos().setPosX(this.getPos().getPosX() + getVelX());
		this.getPos().setPosY(this.getPos().getPosY() + getVelY());
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}