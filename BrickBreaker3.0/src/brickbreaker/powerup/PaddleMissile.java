package brickbreaker.powerup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import javax.swing.ImageIcon;

import brickbreaker.model.Brick;
import brickbreaker.model.Item;
import brickbreaker.model.Paddle;
import brickbreaker.view.Game;

public class PaddleMissile extends PowerUp {
	
	private int millisBetweenShoots = 300;
	private int shoots = millisBetweenShoots;
	private ArrayList<Missile> missileList;
	private Item itemToAttack;
	private Missile myMissile;
	private ArrayList<Item> entities;
	
	public PaddleMissile(Integer x, Integer y, Item itemToEffect, ArrayList<Item> entities) {
		super(x, y, itemToEffect);
		missileList = new ArrayList<Missile>();
		this.entities = entities;
		this.setCoolDown(10);
		Image img = new ImageIcon(this.getClass().getResource("/res/images/powerups/paddlemissle.png")).getImage();
		this.setImg(img);
		this.setEffect(new Callable<Boolean>() {
			public Boolean call() {
		        return ((Paddle) getItemToEffect()).isEffectMissile();
			}
		});
	}
	
	@Override
	public void update() {
		for (Missile m : missileList) {
			if(m.isVisible()) {
				m.update();
				for (int i = 0; i < entities.size(); i++) {
					Item it = entities.get(i);
					if (it instanceof Brick) {
						m.colission(it);
						if (((Brick) it).getIsSmashed()) {
							entities.remove(i);
						}
					}
				}
			}
		}
		if(isVisible()) {
			this.getPos().setPosY(this.getPos().getPosY() + getVelY());
		}
		if (!((Paddle) getItemToEffect()).isEffectMissile() && this.isCollected()) {
			this.setTimeStart(Instant.now());
				startEffect();
		}else if (this.getTimeStart() != null && this.isCollected() && this.checkIfExpired()) {
			stopEffect();
			this.setCollected(false);
		} else if (this.isCollected()){
			startEffect();
		}
	}
	
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		if (this.isVisible()) {
			gfx.drawImage(getImg(), x, y, w, h,null);
		}
		
//		if (myMissile != null) {
//			gfx.setColor(Color.white);
//			//gfx.fillRect(myMissile.getPos().getPosX(), myMissile.getPos().getPosY(), 10, 20);
//			myMissile.draw(gfx);
//			myMissile.colission(itemToAttack);
//		}
		
		for (Missile m : missileList) {
			if(m.isVisible()) {
				m.draw(gfx);
			}
		}
		
	}
	

	@Override
	public void startEffect() {
			((Paddle) this.getItemToEffect()).setEffectMissile(true);
			if (this.getTimePastBetween().toMillis() >= shoots) {
			shoots += millisBetweenShoots;
			Missile myMissile = new Missile(getItemToEffect().getPos().getPosX(), getItemToEffect().getPos().getPosY()-20, 10, 20);
			Missile myMissile2 = new Missile(getItemToEffect().getPos().getPosX()+(getItemToEffect().getPos().getWidth()-10), getItemToEffect().getPos().getPosY()-20, 10, 20);
			missileList.add(myMissile);
			missileList.add(myMissile2);
		}
	}

	@Override
	public void stopEffect() {
		((Paddle) this.getItemToEffect()).setEffectMissile(false);
		missileList.clear();
		this.setCollected(false);
		this.setDead(true);
	}
}
