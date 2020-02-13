package Model;

public class Enemy {

	private double x;
	private double y;
	private int hitpoints = 10;
	private Bomb bomb;
	
	public Enemy(double x, double y) {
		initEnemy(x,y);
	}
	
	private void initEnemy(double x, double y) {
		this.x = x;
		this.y = y;
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
	protected int getHitpoints() {
		return hitpoints;
	}
	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	
}
