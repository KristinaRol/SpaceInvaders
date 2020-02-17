package Model;

import javax.swing.ImageIcon;

public class Enemy {

	private double x;
	private double y;
	private int hitpoints = 10;
	private int row;

	public Enemy(double x, double y, int row) {
		initEnemy(x, y, row);
	}

	private void initEnemy(double x, double y, int row) {
		this.x = x;
		this.y = y;
		this.row = row;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public int getRow() {
		return row;
	}

	protected int getHitpoints() {
		return hitpoints;
	}

	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	public void remove() {
		hitpoints = 0;
	}

}
