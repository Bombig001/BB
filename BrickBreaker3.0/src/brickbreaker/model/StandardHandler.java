package brickbreaker.model;

import java.awt.Graphics;

public interface StandardHandler {
	
	public void colission(Item i);
	public void draw(Graphics gfx);
	public void update();
}
