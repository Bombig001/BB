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
	private int defSpeed;
	private int speed;
	private Image state0;
	private static Sound ballSound;
	private Players playertyp;
	private boolean ballStoped;
	private Instant timeStart;
	private Instant timeStop;
	private Duration timePastBetween;

	public Ball(Integer x, Integer y, Integer w, Integer h, int i, Players playertyp) {
		super(x, y, w, h, 1);
		defSpeed = 7;
		speed = 7;
		ballStoped = true;
		this.setVelX(-speed);
		this.setVelY(speed);
		//state0 = Toolkit.getDefaultToolkit().getImage("res/ball/ball0.png");
		if (playertyp == Players.PLAYER1) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball0.png")).getImage(); 
		} else {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball1.png")).getImage();
		}
		ballSound = new Sound("/res/sounds/bounce.wav",-10.0f);
		this.playertyp = playertyp;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
		this.setVelX(-speed);
		this.setVelY(speed);
	}

	public void respawn() {
		timeStop = Instant.now();
		timePastBetween = Duration.between(timeStart, timeStop);
		
		if (timePastBetween.getSeconds() >= 2) {
			setSpeed(defSpeed);
			ballStoped = true;
			this.getPos().setPosX(300);
			this.getPos().setPosY(500);
			timeStart = null;
		}
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

	@Override
	public boolean colission(Item i) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
//		int x_ = i.getPos().getPosX().intValue();
//		int y_ = i.getPos().getPosY().intValue();
//		int w_ = i.getPos().getWidth().intValue();
//		int h_ = i.getPos().getHeight().intValue();
		
		if (Game.multiplayerGameStarted && playertyp == Players.PLAYER1) {
			if (x-speed <= GameController.windowWidth/2+8) {
				this.setVelX(speed);
				ballSound.start();
			}
			
			if (x+w+speed >= GameController.windowWidth) {
				this.setVelX(-speed);
				ballSound.start();
			}
			
			if (y-speed <= 50) {
				this.setVelY(speed);
				ballSound.start();
			}
			
			if (y+h >= GameController.windowHeight) {
				if (timeStart == null) {
					timeStart = Instant.now();
					this.setSpeed(0);
					this.setPos((int)(GameController.windowWidth/1.4), GameController.windowHeight);
				}
				respawn();
			}	
		}
		
		if (playertyp == Players.PLAYER2 || playertyp == Players.COMPUTER || Game.singleplayerGameStarted) {
			if (x-speed <= 0) {
				this.setVelX(speed);
				ballSound.start();
			}
			
			if (x+w >= Math.max(720,GameController.windowWidth/2-8)) {
				this.setVelX(-speed);
				ballSound.start();
			}
			
			if (y-speed <= 50) {
				this.setVelY(speed);
				ballSound.start();
			}
			
			if (y+h >= GameController.windowHeight) {
				if (timeStart == null) {
					timeStart = Instant.now();
					setSpeed(0);
					this.setPos(GameController.windowWidth/5, GameController.windowHeight);
				}
				respawn();
			}	
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
	}
	
	@Override
	public void update() {
		if (!ballStoped) {
			this.getPos().setPosX(this.getPos().getPosX() + getVelX());
			this.getPos().setPosY(this.getPos().getPosY() + getVelY());
		}
	}

}