package brickbreaker.model;

import java.awt.Graphics;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;

import javax.swing.ImageIcon;

import brickbreaker.controller.GameController;
import brickbreaker.player.Players;
import brickbreaker.sound.Sound;
import brickbreaker.view.Game;

public class Ball extends Item {
	private static Sound ballSound = new Sound("/res/sounds/bounce.wav",-10.0f);
	private Game game;
	private int defSpeed;
	private int speed;
	private Image state0;
	private Players playertyp;
	private boolean ballStoped;
	private Instant timeStart;
	private Instant timeStop;
	private Duration timePastBetween;
	private boolean effectMelting;
	private boolean effectFast;
	private boolean effectSlow;

	public Ball(Integer x, Integer y, Integer w, Integer h, int i, Players playertyp, Game game) {
		super(x, y, w, h);
		this.game = game;
		defSpeed = 7;
		speed = 7;
		ballStoped = true;
		this.initVelocity();
		//state0 = Toolkit.getDefaultToolkit().getImage("res/ball/ball0.png");
		if (playertyp == Players.PLAYER1) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball0.png")).getImage(); 
		} else {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball1.png")).getImage();
		}
		
		this.playertyp = playertyp;
		resetEffects();
	}

	public int getDefSpeed() {
		return defSpeed;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void respawn() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		
		if (timePastBetween.getSeconds() >= 2) {
			this.resetEffects();
			this.setSpeed(getDefSpeed());
			this.initVelocity();
			ballStoped = true;
			this.getPos().setPosX(300);
			this.getPos().setPosY(500);
			timeStart = null;
		}
	}
	
	public void initVelocity() {
		this.setVelX(-speed);
		this.setVelY(speed);
	}
	
	public void resetEffects() {
		effectMelting = false;
		effectFast = false;
		effectSlow = false;
	}
	
	public static Sound getBallSound() {
		return ballSound;
	}
	
	
	public void setPlayertyp(Players playertyp) {
		this.playertyp = playertyp;
	}

	public boolean isBallStoped() {
		return ballStoped;
	}

	public void setBallStoped(boolean ballStoped) {
		this.ballStoped = ballStoped;
	}

	public boolean isEffectMelting() {
		return effectMelting;
	}

	public void setEffectMelting(boolean effectMelting) {
		this.effectMelting = effectMelting;
	}

	public boolean isEffectFast() {
		return effectFast;
	}

	public void setEffectFast(boolean effectFast) {
		this.effectFast = effectFast;
	}

	public boolean isEffectSlow() {
		return effectSlow;
	}

	public void setEffectSlow(boolean effectSlow) {
		this.effectSlow = effectSlow;
	}

	@Override
	public void colission(Item i) {
		int windowwidth;
		if (GameController.getWidth().intValue() == GameController.getDefWidth().intValue()) {
			windowwidth = GameController.getWidth().intValue()/2;
		} else {
			windowwidth = GameController.getWidth().intValue();
		}
		
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		
		if (game.isMultiplayerGame() && playertyp == Players.PLAYER1) {
			if (x-speed <= (GameController.getWidth().intValue() + 16) / 2) {
				this.setVelX(speed);
				ballSound.start();
			}
			
			if (x+w+speed >= GameController.getDefWidth().intValue()) {
				this.setVelX(-speed);
				ballSound.start();
			}
			
			if (y-speed <= 50) {
				this.setVelY(speed);
				ballSound.start();
			}
			
			if (y+h >= GameController.getHeight().intValue()) {
				if (timeStart == null) {
					timeStart = Instant.now();
					this.setSpeed(0);
					this.initVelocity();
					this.setPos((int)(GameController.getWidth().intValue()/1.4), GameController.getHeight().intValue());
				}
				respawn();
			}	
		}
		
		if (playertyp == Players.PLAYER2 || playertyp == Players.COMPUTER || game.isSingleplayerGame()) {
			if (x-speed <= 0) {
				this.setVelX(speed);
				ballSound.start();
			}
			if (x+w >= windowwidth) {
				this.setVelX(-speed);
				ballSound.start();
			}
			
			if (y-speed <= 50) {
				this.setVelY(speed);
				ballSound.start();
			}
			
			if (y+h >= GameController.getHeight().intValue()) {
				if (timeStart == null) {
					timeStart = Instant.now();
					setSpeed(0);
					this.initVelocity();
					this.setPos(GameController.getWidth().intValue()/5, GameController.getHeight().intValue());
				}
				respawn();
			}	
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
		if (!ballStoped) {
			this.getPos().setPosX(this.getPos().getPosX() + getVelX());
			this.getPos().setPosY(this.getPos().getPosY() + getVelY());
		}
	}

}