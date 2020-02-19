package Model;

public class Shoot {

	private int x, y;

	public Shoot(int x, int y) {
		this.x = x;
		this.y = y;
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

	public boolean isAlive() {
		return y > -2;
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
