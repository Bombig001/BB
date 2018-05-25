package brickbreaker.model;

import brickbreaker.position.Position;

/**
 * Diese Klasse dient dazu, ein Itemobjekt zu erstellen.
 * 
 * @author Savas Celik
 *
 */
public abstract class Item implements StandardHandler{
	
	/**
	 * Instanzvariable, die den pos haelt.
	 */
	private Position pos;
	
	/**
	 * Instanzvariable, die den velX haelt.
	 */
	private Integer velX;
	
	/**
	 * Instanzvariable, die den velY haelt.
	 */
	private Integer velY;
	
	/**
	 * Konstruktor
	 * 
	 * @param x - Ein Integer, welcher die x Position beschreibt.
	 * @param y - Ein Integer, welcher die y Position beschreibt.
	 * @param w	- Ein Integer, welcher die Breite beschreibt.
	 * @param h	- Ein Integer, welcher die Hoehe beschreibt.
	 */
	public Item (Integer x, Integer y, Integer w, Integer h) {
		pos = new Position(x,y,w,h);
		velX = 0;
		velY = 0;
	}
	
	/**
	 * Liefert die pos als Position.
	 * 
	 * @return pos als Position.
	 */
	public Position getPos() {
		return pos;
	}
	
	/**
	 * @param x - Ein Integer, welcher die x Position beschreibt.
	 * @param y - Ein Integer, welcher die y Position beschreibt.
	 */
	public void setPos(Integer x, Integer y) {
		pos.setPosX(x);
		pos.setPosY(y);
	}

	/**
	 * Liefert die velX als Integer.
	 * 
	 * @return velX als Integer.
	 */
	public Integer getVelX() {
		return velX;
	}

	/**
	 * @param velX - Ein Integer, welcher die x Geschwindigkeit beschreibt.
	 */
	public void setVelX(Integer velX) {
		this.velX = velX;
	}

	/**
	 * Liefert die velY als Integer.
	 * 
	 * @return velY als Integer.
	 */
	public Integer getVelY() {
		return velY;
	}

	/**
	 * @param velX - Ein Integer, welcher die y Geschwindigkeit beschreibt.
	 */
	public void setVelY(Integer velY) {
		this.velY = velY;
	}
}
