package Model;

public class Explosion {

	private int x, y;
	private int state = 0;
	
	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void nextState() {
		state++;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getState() {
		return state;
	}
}
