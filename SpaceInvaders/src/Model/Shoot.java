package Model;

public class Shoot {

	private double x, y;
	
	public Shoot(double x, double y) {
		this.x = x;
		this.y = y;
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

	public boolean isAlive() {
		return y>-2;
	}
	
	public boolean hitsEnemy(Enemies enemies) {
		
		for (Enemy enemy : enemies.getEnemmyList()) {
			if (x == enemy.getX()) {
				if (y == enemy.getY()) {
					enemy.remove();
					return true;
				}
			}
		}
		
		
		return false;
	}
}
