package Test;

import acm.graphics.GRect;

public class Spaceship {
	
	private double x;
	private double y;
	private int life;
	public GRect rect;
	
	public Spaceship (double x, double y) {
		this.x = x;
		this.y = y;
		this.life = 5;
		rect = new GRect(x,y,x,y);
	}


	protected double getX() {
		return x;
	}
	protected void setX(double x) {
		this.x = x;
	}
	protected double getY() {
		return y;
	}
	protected void setY(double y) {
		this.y = y;
	}
	protected int getLife() {
		return life;
	}
	protected void setLife(int life) {
		this.life = life;
	}

	public void moveL() {
		this.rect.setLocation(x-10, y);
	}
	public void moveR() {
		this.rect.setLocation(x+10, y);
	}
	
	

}
