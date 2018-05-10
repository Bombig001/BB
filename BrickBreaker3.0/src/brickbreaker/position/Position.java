package brickbreaker.position;

public class Position {
	private Integer posX;
	private Integer posY;
	private Integer width;
	private Integer height;
	
	
	public Position(Integer x, Integer y, Integer w, Integer h)  {
		posX = x;
		posY = y;
		width = w;
		height = h;
	}


	public Integer getPosX() {
		return posX;
	}


	public void setPosX(Integer posX) {
		this.posX = posX;
	}


	public Integer getPosY() {
		return posY;
	}


	public void setPosY(Integer posY) {
		this.posY = posY;
	}


	public Integer getWidth() {
		return width;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}


	public Integer getHeight() {
		return height;
	}


	public void setHeight(Integer height) {
		this.height = height;
	}
}
