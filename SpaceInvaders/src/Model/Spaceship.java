package Model;

public class Spaceship {
	
	private double x;
	private double y;
	private int life;
	
	public Spaceship (double x, double y) {
		this.x = x;
		this.y = y;
		this.life = 5;
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
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
}
