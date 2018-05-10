package brickbreaker.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import brickbreaker.controller.GameController;
import brickbreaker.sound.Sound;

public class Paddle extends Item {
	private String name;
	private Integer score;
	private int stateCounter = 0;
	private Image img;
	private Image state0;
	private Image state1;
	private Image state2;
	private JComponent game;
	private TastaturEingabe tast;
	private boolean enLargedEffect;
	private int speed;
	private static Sound paddleToBallSound;

	public Paddle(JComponent game,String name, Integer x, Integer y, Integer w, Integer h) {
		super(x, y, w, h, 3);
		enLargedEffect = false;
		this.name = name;
		speed = 7;
		tast = new TastaturEingabe(game);
		state0  = new ImageIcon(this.getClass().getResource("/res/images/paddle/state0.png")).getImage();
		state1  = new ImageIcon(this.getClass().getResource("/res/images/paddle/state1.png")).getImage();
		state2  = new ImageIcon(this.getClass().getResource("/res/images/paddle/state2.png")).getImage();
		paddleToBallSound = new Sound("/res/sounds/bouncePaddle.wav",-10.0f);
	}
	
	public Integer getScore() {
		return score;
	}



	public void setScore(Integer score) {
		this.score = score;
	}

	public static Sound getPaddleToBallSound() {
		return paddleToBallSound;
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
		
		if (x <= 0) {
			this.getPos().setPosX(0);
		}
		
		if (x+w+speed >= GameController.windowWidth) {
			this.getPos().setPosX(GameController.windowWidth - w-speed);
		}
		
		// colission test
		  if (x_+w_ >= x && x_ <= x+w && y_+h_ >= y && y_ <= y+h){
			    if (y_ >= y) {
			    	i.setVelY(((Ball) i).getSpeed());
			    }else{
			    	i.setVelY(-((Ball) i).getSpeed());
			    }
			    if(x_+w_ <= x+10) {
			    	i.setVelX(-((Ball) i).getSpeed());
			    } else if(x_ >= x+w-10) {
			    	i.setVelX(((Ball) i).getSpeed());
			    }
			    paddleToBallSound.start();
			    return true;
			  }
		
		return false;
	}
	
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		//gfx.drawImage(state0, x, y, w, h, null);		
		
		gfx.setColor(new Color(0,0,0,150));
		gfx.fillOval(x+7, y+7, w, h);
		
		if (stateCounter <= 10) {
			img = state0;
			//gfx.drawImage(state1, x, y, w, h, null);
		} else if (stateCounter <= 20) {
			img = state1;
			//gfx.drawImage(state1, x, y, w, h, null);
		} else if (stateCounter <= 30) {
			img = state2;
			//gfx.drawImage(state2, x, y, w, h, null);
		} else if (stateCounter <= 40) {
			//gfx.drawImage(state2, x, y, w, h, null);
			stateCounter = 0;
		}
		gfx.drawImage(img, x, y, w, h, null);
		stateCounter++;
	}
	
	@Override
	public void update() {
		tast.tasteGedrückt(KeyEvent.VK_LEFT,"links", (evt) -> {
			this.setVelX(-speed);
		});
		tast.tasteLosgelassen(KeyEvent.VK_LEFT,"linksStop", (evt) -> {
			this.setVelX(0);
		});
		
		tast.tasteGedrückt(KeyEvent.VK_RIGHT,"rechts", (evt) -> {
			this.setVelX(speed);
		});
		tast.tasteLosgelassen(KeyEvent.VK_RIGHT,"rechtsStop", (evt) -> {
			this.setVelX(0);
		});
		
		this.getPos().setPosX(this.getPos().getPosX() + getVelX());
		this.getPos().setPosY(this.getPos().getPosY() + getVelY());
	}

	public boolean isEnLargedEffect() {
		return enLargedEffect;
	}

	public void setEnLargedEffect(boolean enLargedEffect) {
		this.enLargedEffect = enLargedEffect;
	}

}
