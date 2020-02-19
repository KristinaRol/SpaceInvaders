package Model;

public class Bomb {
	
	private int x, y;

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move() {
		if (isVisible()) {
			y++;
		}
	}
	
	public boolean isVisible() {
		if (y <= Spaceship.BASE_HEIGHT) {
			return true;
		}
		else {
			return false;			
		}
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
	
	
}
