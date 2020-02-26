package Model;

public class Shoot {

	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_LEFT = 1;
	public static final int DIRECTION_RIGHT = 2;
	private int x, y;
	private int direction;
	

	public Shoot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
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
	
	public int getDirection() {
		return direction;
	}

	/**
	 * If the shoot is on the same position as an enemy, the enemy is removed from the list and true is returned.
	 */
	public boolean hitsEnemy(Enemies enemies) {
			for (Enemy enemy : enemies.getEnemmyList()) {
				if (x == enemy.getX()) {
					if (y == enemy.getY()) {
						Sound.play("bomb.wav");
						enemy.remove();
						return true;
					}
				}
			}
		return false;
	}
	
	public boolean  isVisible() {
		if (x < 0 || x > Spaceship.BASE_WIDTH || y < -1) {
			return false;
		}
		
		return true;
	}
	
}