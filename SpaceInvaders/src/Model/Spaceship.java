package Model;

import java.util.ArrayList;

public class Spaceship {
	
	public final static int BASE_WIDTH = 28;
	public final static int BASE_HEIGHT = 14;
	public final static int MULTIPLIER = 50;

	private int x;
	private int y;
	private int life;
	
	//-1 = lose, 0 = normal, 1 = win.
	private int winLose = 0;
	
	public ArrayList<Shoot> shoots = new ArrayList<>();
	public ArrayList<Explosion> explosionList = new ArrayList<>();


	
	public Spaceship() {
		this.x = 13;
		this.y = 12;
		this.life = 3;
	}

	
	public void shoot() {
		Shoot shoot = new Shoot(x, y);
		shoots.add(shoot);
	}

	
	public void moveLeft() {
		if (x > 0) {
			setX(x - 1);			
		}
	}
	
	public void moveRight() {
		if (x < getMaxXFromShip()) {
			setX(x + 1);
		}
	}
	
	
	
	public void shootEnemy(Enemies enemies) {
		for (Shoot shoot : shoots) {
			shoot.setY(shoot.getY() - 1);
			if (shoot.hitsEnemy(enemies)) {
				
				Explosion explosion = new Explosion(shoot.getX(), shoot.getY());
				explosionList.add(explosion);
				
				shoot.setY(-5);
			}
		}
	}
	
	
	
	public void checkGameState(Enemies enemies) {
		
		if (enemies.areAllDead()) {
			winLose = 1;
		}
		else if (life <= 0) {
			winLose = -1;
		}
	}

	
	public boolean won() {
		return winLose == 1;
	}

	public boolean lost() {
		return winLose == -1;
	}
	
	
	
	public void nextStateExplosion() {
		for(Explosion explosion : explosionList) {
			explosion.nextState();
		}
	}

	public void removeExplosions() {
		@SuppressWarnings("unchecked")
		ArrayList<Explosion> explosionCheckList = (ArrayList<Explosion>) explosionList.clone();
		for(Explosion explosion : explosionCheckList) {
			if (explosion.getState() > 3) {
				explosionList.remove(explosion);
			} 
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
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public ArrayList<Explosion> getExplosions() {
		return explosionList;
	}

	public double getVelocityMultiplier() {
		return 0;
	}
	public int getBaseWidth() {
		return BASE_WIDTH;
	}
	public int getBaseHeight() {
		return BASE_HEIGHT;
	}
	public int getIntWidth() {
		return BASE_WIDTH * MULTIPLIER + 16;
	}
	public int getIntHeight() {
		return BASE_HEIGHT * MULTIPLIER + 63;
	}
	public double getMaxXFromShip() {
		return BASE_WIDTH-1;
	}



	
}
