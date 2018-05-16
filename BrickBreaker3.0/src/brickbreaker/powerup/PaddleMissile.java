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
import brickbreaker.player.Player;
import brickbreaker.view.Game;

public class PaddleMissile extends PowerUp {
	
	private int millisBetweenShoots = 300;
	private int shoots = millisBetweenShoots;
	private ArrayList<Missile> missileList;
	private Player player;
	
	public PaddleMissile(Integer x, Integer y, Item itemToEffect, Player player) {
		super(x, y, itemToEffect);
		missileList = new ArrayList<Missile>();
		this.player = player;
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
				for (int i = 0; i < player.getEntities().size(); i++) {
					Item it = player.getEntities().get(i);
					if (it instanceof Brick) {
						m.colission(it);
						if (((Brick) it).getIsSmashed()) {
							player.getEntities().remove(i);
							player.setScore(player.getScore()+10);
							i--;
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
			this.setDead(true);
		} else if (this.getTimeStart() != null && this.isCollected()){
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
		
		for (Missile m : missileList) {
			if(m.isVisible()) {
				m.draw(gfx);
			}
		}
		
	}
	

	@Override
	public void startEffect() {
			((Paddle) this.getItemToEffect()).setEffectMissile(true);
			if (this.getTimePastBetween().getSeconds() < 8) {
				if (this.getTimePastBetween().toMillis() >= shoots) {
				shoots += millisBetweenShoots;
				Missile myMissile = new Missile(getItemToEffect().getPos().getPosX(), getItemToEffect().getPos().getPosY()-20, 10, 20);
				Missile myMissile2 = new Missile(getItemToEffect().getPos().getPosX()+(getItemToEffect().getPos().getWidth()-10), getItemToEffect().getPos().getPosY()-20, 10, 20);
				missileList.add(myMissile);
				missileList.add(myMissile2);
			}
		}
	}

	@Override
	public void stopEffect() {
		((Paddle) this.getItemToEffect()).setEffectMissile(false);
		missileList.clear();
	}
}
