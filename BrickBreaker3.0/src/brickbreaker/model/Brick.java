package brickbreaker.model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import brickbreaker.sound.Sound;

/**
 * Diese Klasse dient dazu, ein Brickobjekt zu erstellen.
 * 
 * @author Savas Celik
 *
 */
public class Brick extends Item {
	
	/**
	 * Klassenvariablen, die den brickToBallSound haelt.
	 */
	private static Sound brickToBallSound0 = new Sound("/res/sounds/bounceBrick0.wav",-10.0f);
	private static Sound brickToBallSound1 = new Sound("/res/sounds/bounceBrick1.wav",-10.0f);
	
	/**
	 * Instanzvariable, die den Zustand für smashed haelt.
	 */
	private boolean smashed;
	
	/**
	 * Instanzvariable, die den health haelt.
	 */
	private Integer health;
	
	/**
	 * Instanzvariablen, die das Bild für die Bricks haelt.
	 */
	private Image state0, state1, state2;
	
	/**
	 * Konstruktor
	 * 
	 * @param x - Ein Integer, welcher die x Position beschreibt.
	 * @param y - Ein Integer, welcher die y Position beschreibt.
	 * @param w	- Ein Integer, welcher die Breite beschreibt.
	 * @param h	- Ein Integer, welcher die Hoehe beschreibt.
	 * @param color - Ein Integer, welcher die Farbe beschreibt.
	 */
	public Brick(Integer x, Integer y, Integer w, Integer h, Integer color) {
		super(x, y, w, h);
		this.health = 1;
		if ( color == 1 ) {
			this.setHealth(3);
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey0.png")).getImage();
			state1 = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey1.png")).getImage();
			state2 = new ImageIcon(this.getClass().getResource("/res/images/bricks/grey2.png")).getImage();
		} else if ( color == 2) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/blau1.png")).getImage();
		} else if ( color == 3 ) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/rot1.png")).getImage();
		} else if ( color == 4) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/yellow1.png")).getImage();
		} else if ( color == 5 ) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/brown1.png")).getImage();
		} else if ( color == 6 ) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/orange1.png")).getImage();
		} else if ( color == 7 ) {
			state0 = new ImageIcon(this.getClass().getResource("/res/images/bricks/green1.png")).getImage();
		}
	}
	
	/**
	 * Liefert das aktuelle Leben des Bricks als Integer.
	 * 
	 * @return health als Integer.
	 */
	private Integer getHealth() {
		return health;
	}
	
	/**
	 * @param health - Aendert den Lebenswert des Bricks.
	 */
	private void setHealth(Integer health) {
		this.health = health;
	}

	/**
	 * Fuegt dem Brick Schaden zu.
	 */
	public void dealDamage() {
		if(this.getHealth() > 1) {
			this.setHealth(this.getHealth().intValue() - 1);
		    brickToBallSound1.start();
		} else {
			this.smashed = true;
			brickToBallSound0.start();
		}
	}
	
	/**
	 * Liefert den Zustand von smashed als boolean.
	 * 
	 * @return smashed als boolean.
	 */
	public boolean isSmashed() {
		return smashed;
	}
	
	/**
	 * Klassenmethode, Liefert den brickToBallSound0 als Sound.
	 * 
	 * @return brickToBallSound0 als Sound.
	 */
	public static Sound getBrickToBallSound0() {
		return brickToBallSound0;
	}
	
	/**
	 * Klassenmethode, Liefert den brickToBallSound1 als Sound.
	 * 
	 * @return brickToBallSound1 als Sound.
	 */
	public static Sound getBrickToBallSound1() {
		return brickToBallSound1;
	}

	/**
	 * Die Methode draw von StandardHanler wird überschrieben und für den Brick angepasst.
	 * 
	 * @see StandardHandler
	 */
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
	
	/**
	 * Die Methode colission von StandardHanler wird überschrieben und für den Brick angepasst.
	 * 
	 * @see StandardHandler
	 */
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
		
		 if (x_+w_ >= x && x_ <= x+w && y_+h_ >= y && y_ <= y+h){
			 if (!((Ball) i).isEffectMelting()) {
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
			 }
			 dealDamage();
			 }
	}
	
	/**
	 * Die Methode colission von StandardHanler wird überschrieben.
	 * 
	 * @see StandardHandler
	 */
	@Override
	public void update() {
		
	}
}
