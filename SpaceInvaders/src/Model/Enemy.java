package Model;

import javax.swing.ImageIcon;

public class Enemy {

	private double x;
	private double y;
	private int hitpoints = 10;
	private Bomb bomb;

	public Enemy(double x, double y) {
		initEnemy(x, y);
	}

	// wird noch fertig gestellt mit bomb und so
	private void initEnemy(double x, double y) {
		this.x = x;
		this.y = y;

		bomb = new Bomb(x, y);
	}

	public void move(double y) {
		this.y += y;
	}


	public Bomb getBomb() {
		return bomb;
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

	protected int getHitpoints() {
		return hitpoints;
	}

	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public class Bomb {
		private boolean destroyed = false;
		double x, y;

		public Bomb(double x, double y) {
			setDestroyed(true);
			this.x = x;
			this.y = y;
		}
		
		public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {

            return destroyed;
        }
	}

}
