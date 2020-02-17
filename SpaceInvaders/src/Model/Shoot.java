package Model;

import View.Board;
import View.BoardFancy;

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
		return y > -2;
	}

	
	public boolean hitsEnemy(Enemies enemies, Board board) {
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
	
	public boolean hitsEnemy(Enemies enemies, BoardFancy board) {
		for (Enemy enemy : enemies.getEnemmyList()) {
			if (x > enemy.getX()*21 && x < enemy.getX()*21 + 30) {
				if (y > enemy.getY()*21 && y < enemy.getY()*21 +30) {
					enemy.remove();
					return true;
				}
			}
		}
		return false;
	}
	
}
