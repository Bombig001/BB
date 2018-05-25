package brickbreaker.model;

import java.awt.Graphics;

/**
 * Diese Klasse dient dazu, ein Itemobjekt zu erstellen.
 * 
 * @author Savas Celik
 *
 */
public interface StandardHandler {
	
	/**
	 * @param velX - Ein Item, welcher die Kollidierenden Item beschreibt.
	 */
	public void colission(Item i);
	
	/**
	 * @param gfx - Ein Graphics, welcher die Graphics beschreibt.
	 */
	public void draw(Graphics gfx);
	
	/**
	 * Diese Methode dient dazu, das jeweilige Objekt zu bewegen bzw. Animieren.
	 */
	public void update();
}
