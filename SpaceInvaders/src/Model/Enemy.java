package Model;

public class Enemy {

	private double x;
	private double y;
	private int hitpoints = 10;
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
	protected int getHitpoints() {
		return hitpoints;
	}
	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	
}
