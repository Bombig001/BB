package brickbreaker.model;

import java.awt.Graphics;

public interface ItemHandler {
	public void colission(Item i);
	public void draw(Graphics gfx);
	public void update();
}
