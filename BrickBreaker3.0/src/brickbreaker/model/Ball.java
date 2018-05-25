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

/**
 * Diese Klasse dient dazu, ein Ballobjekt zu erstellen.
 * 
 * @author Savas Celik
 *
 */
public class Ball extends Item {
	/**
	 * Klassenvariable, fuer den Sound.
	 */
	private static Sound ballSound = new Sound("/res/sounds/bounce.wav",-10.0f);
	
	/**
	 * Instanzvariable, fuer das Game.
	 */
	private Game game;
	
	/**
	 * Instanzvariable, fuer die Standardgeschwindigkeit des Balles.
	 */
	private int defSpeed;
	
	/**
	 * Instanzvariable, fuer die aktuelle Geschwindigkeit des Balles.
	 */
	private int speed;
	
	/**
	 * Instanzvariable, ein Image für das Aussehen des Balles.
	 */
	private Image state0;
	
	/**
	 * Instanzvariable, die den playertyp haelt.
	 */
	private Players playertyp;
	
	/**
	 * Instanzvariable, der sagt, ob der Ball gestoppt ist oder nicht.
	 */
	private boolean ballStoped;
	
	/**
	 * Instanzvariable, welches die Startzeit der Strafe haelt.
	 */
	private Instant punishmentTimeStart;
	
	/**
	 * Instanzvariable, welches die Stoppzeit der Strafe haelt.
	 */
	private Instant punishmentTimeStop;
	
	/**
	 * Instanzvariable, welches die vergangene Zeit healt.
	 */
	private Duration timePastBetween;
	
	/**
	 * Instanzvariable, die den effect Zustand haelt.
	 */
	private boolean effectMelting, effectFast, effectSlow;
	
	/**
	 * Konstruktor
	 * 
	 * @param x - Ein Integer, welcher die x Position beschreibt.
	 * @param y - Ein Integer, welcher die y Position beschreibt.
	 * @param w	- Ein Integer, welcher die Breite beschreibt.
	 * @param h	- Ein Integer, welcher die Hoehe beschreibt.
	 * @param playertyp	- Ein Enum, welcher den spielertyp beschreibt. Z.B. <code>Players.PLAYER1</code>.
	 * @param game - Ein Game, welcher das aktuelle Spiel beschreibt.
	 */
	public Ball(Integer x, Integer y, Integer w, Integer h, Players playertyp, Game game) {
		super(x, y, w, h);
		this.game = game;
		defSpeed = 7;
		speed = 7;
		ballStoped = true;
		this.initVelocity();
		if (playertyp == Players.PLAYER1) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball0.png")).getImage(); 
		} else {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/ball/ball1.png")).getImage();
		}
		
		this.playertyp = playertyp;
		resetEffects();
	}
	
	
	/**
	 * Liefert die Standardgeschwindigkeit des Balles als int.
	 * 
	 * @return defSpeed als int.
	 */
	public int getDefSpeed() {
		return defSpeed;
	}
	
	/**
	 * Liefert die aktuelle Geschwindigkeit des Balles als int.
	 * 
	 * @return speed als int.
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * @param speed - Aendert die die aktuelle Geschwindigkeit des Balles.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Falls der Ball nicht abgefangen wird muss man sie mit dieser Methode wieder respawnen.
	 *  
	 * respawnt denn Ball nach 2 Sekunden.
	 */
	public void respawn() {
		punishmentTimeStop = Instant.now();
		timePastBetween = Duration.between(punishmentTimeStart, punishmentTimeStop);
		
		if (timePastBetween.getSeconds() >= 2) {
			this.resetEffects();
			this.setSpeed(getDefSpeed());
			this.initVelocity();
			ballStoped = true;
			this.getPos().setPosX(300);
			this.getPos().setPosY(500);
			punishmentTimeStart = null;
		}
	}
	
	/**
	 * Initialisiert die Geschwindigkeit des Balles.
	 */
	public void initVelocity() {
		this.setVelX(-speed);
		this.setVelY(speed);
	}
	
	/**
	 * Alle Effekte, die der Ball zurzeit hat wird entfert.
	 * 
	 * Der Zustand wechselt auf false.
	 */
	public void resetEffects() {
		effectMelting = false;
		effectFast = false;
		effectSlow = false;
	}
	
	/**
	 * Liefert den ballSound als Sound.
	 * @return ballSound als Sound.
	 */
	public static Sound getBallSound() {
		return ballSound;
	}
	
	/**
	 * @param playertyp - Aendert den aktuellen spielertypen.
	 */
	public void setPlayertyp(Players playertyp) {
		this.playertyp = playertyp;
	}

	/**
	 * Liefert den Zustand von ballStoped als boolean.
	 * 
	 * @return ballStoped als boolean.
	 */
	public boolean isBallStoped() {
		return ballStoped;
	}
	
	/**
	 * @param ballStoped - Aendert den aktuellen Zustand von ballStoped.
	 */
	public void setBallStoped(boolean ballStoped) {
		this.ballStoped = ballStoped;
	}
	
	/**
	 * Liefert den Zustand von effectMelting als boolean.
	 * 
	 * @return effectMelting als boolean.
	 */
	public boolean isEffectMelting() {
		return effectMelting;
	}

	/**
	 * @param effectMelting - Aendert den aktuellen Zustand von effectMelting.
	 */
	public void setEffectMelting(boolean effectMelting) {
		this.effectMelting = effectMelting;
	}

	/**
	 * Liefert den Zustand von effectFast als boolean.
	 * 
	 * @return effectFast als boolean.
	 */
	public boolean isEffectFast() {
		return effectFast;
	}

	/**
	 * @param effectFast - Aendert den aktuellen Zustand von effectFast.
	 */
	public void setEffectFast(boolean effectFast) {
		this.effectFast = effectFast;
	}
	
	/**
	 * Liefert den Zustand von effectSlow als boolean.
	 * 
	 * @return effectSlow als boolean.
	 */
	public boolean isEffectSlow() {
		return effectSlow;
	}
	
	/**
	 * @param effectSlow - Aendert den aktuellen Zustand von effectSlow.
	 */
	public void setEffectSlow(boolean effectSlow) {
		this.effectSlow = effectSlow;
	}

	/**
	 * Die Methode colission von StandardHanler wird überschrieben und für den Ball angepasst.
	 * 
	 * @see StandardHandler
	 */
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
				if (punishmentTimeStart == null) {
					punishmentTimeStart = Instant.now();
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
				if (punishmentTimeStart == null) {
					punishmentTimeStart = Instant.now();
					setSpeed(0);
					this.initVelocity();
					this.setPos(GameController.getWidth().intValue()/5, GameController.getHeight().intValue());
				}
				respawn();
			}	
		}
	}
	
	/**
	 * Die Methode draw von StandardHanler wird überschrieben und für den Ball angepasst.
	 * 
	 * @see StandardHandler
	 */
	@Override
	public void draw(Graphics gfx) {
		int x = this.getPos().getPosX().intValue();
		int y = this.getPos().getPosY().intValue();
		int w = this.getPos().getWidth().intValue();
		int h = this.getPos().getHeight().intValue();
		
		
		gfx.drawImage(state0, x, y, w, h, null);
	}
	
	/**
	 * Die Methode update von StandardHanler wird überschrieben und für den Ball angepasst.
	 * 
	 * @see StandardHandler
	 */
	@Override
	public void update() {
		if (!ballStoped) {
			this.getPos().setPosX(this.getPos().getPosX() + getVelX());
			this.getPos().setPosY(this.getPos().getPosY() + getVelY());
		}
	}

}