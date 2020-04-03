package Model;

public class Enemy {  

	private int x;
	private int y;
	private int hitpoints = 10;
	private int row;

	public Enemy(int x, int y, int row) {
		initEnemy(x, y, row);
	}

	private void initEnemy(int x, int y, int row) {
		this.x = x;
		this.y = y;
		this.row = row;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
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
