package brickbreaker.model;

import java.awt.Graphics;

public interface ItemHandler {
	public boolean colission(Item i);
	public void draw(Graphics gfx);
	public void update();
}
