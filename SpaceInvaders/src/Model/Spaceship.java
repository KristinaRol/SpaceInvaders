package Model;

import java.util.ArrayList;

public class Spaceship {
	
	public final static int BASE_WIDTH = 28;
	public final static int BASE_HEIGHT = 14;
	public final static int MULTIPLIER = 50;

	private int x;
	private int y;
	private int life;
	private boolean epicWeapon = false;
	
	//-1 = lose, 0 = normal, 1 = win.
	private int winLose = 0;
	private boolean start = false;
	
	public ArrayList<Shoot> shoots = new ArrayList<>();
	public ArrayList<Explosion> explosionList = new ArrayList<>();


	
	public Spaceship() {
		this.x = 13;
		this.y = 12;
		this.life = 3;
	}

	
	public void shoot() {
		Shoot shoot = new Shoot(x, y, Shoot.DIRECTION_UP);
		shoots.add(shoot);
	}

	/**
	 * Moves the player left, if possible.
	 */
	public void moveLeft() {
		if (x > 0) {
			setX(x - 1);			
		}
	}
	
	/**
	 * Moves the player right, if possible.
	 */
	public void moveRight() {
		if (x < getMaxXFromShip()) {
			setX(x + 1);
		}
	}
	
	
	/**
	 * Checks if a shoot hits an enemy. Also moves the shoots up.
	 */
	public void shootEnemy(Enemies enemies) {
		// For each shot
				@SuppressWarnings("unchecked")
				ArrayList<Shoot> shootCheckList = (ArrayList<Shoot>) shoots.clone();
				for (Shoot shoot : shootCheckList) {
					// Moves the shoot.
					if (shoot.getDirection() == Shoot.DIRECTION_UP) {
						shoot.setY(shoot.getY() - 1);				
					}
					if (shoot.getDirection() == Shoot.DIRECTION_LEFT) {
						shoot.setX(shoot.getX() - 1);				
					}
					if (shoot.getDirection() == Shoot.DIRECTION_RIGHT) {
						shoot.setX(shoot.getX() + 1);				
					}

					// Places the shoot at a position where it will be removed, if the shoot hits an enemy
					if (shoot.hitsEnemy(enemies)) {

						// Also creates an explosion if the shoot hits.
						Explosion explosion = new Explosion(shoot.getX(), shoot.getY());
						explosionList.add(explosion);

						if (shoot.getDirection() == Shoot.DIRECTION_UP && epicWeapon) {
							Shoot newShoot1 = new Shoot(shoot.getX(), shoot.getY(), Shoot.DIRECTION_LEFT);
							Shoot newShoot2 = new Shoot(shoot.getX(), shoot.getY(), Shoot.DIRECTION_RIGHT);

							shoots.add(newShoot1);
							shoots.add(newShoot2);					
						}

						// Position where the shoot is invisible.
						shoot.setY(-5);
					}
				}
			}
	
	
	/**
	 * Checks if the player already lost or won.
	 * and if lower enemy row doesn't exist, player gets epic weapon.
	*/
	public void checkGameState(Enemies enemies) {
		
		if (enemies.areAllDead()) {
			winLose = 1;
		}
		else if (life <= 0) {
			winLose = -1;
		}
		
		//doesn't work, don't know why
		epicWeapon = true;
		for (Enemy enemy : enemies.getEnemmyList()) {
			if(enemy.getRow()==2) {
				epicWeapon = false;
			}
		}
	}
	
	public boolean getStart() {
		return start;
	}

	public void setStart() {
		start = true;
	}

	
	public boolean won() {
		return winLose == 1;
	}

	public boolean lost() {
		return winLose == -1;
	}
	
	
	/**
	 * Changes the state of every explosion. e.g. from yellow to orange.
	 */
	public void nextStateExplosion() {
		for(Explosion explosion : explosionList) {
			explosion.nextState();
		}
	}

	/**
	 * Removes explosions that are already shown long enough.
	 */
	public void removeExplosions() {
		@SuppressWarnings("unchecked")
		ArrayList<Explosion> explosionCheckList = (ArrayList<Explosion>) explosionList.clone();
		for(Explosion explosion : explosionCheckList) {
			// Removes explosions with a state greater that 3.
			if (explosion.getState() > 3) {
				explosionList.remove(explosion);
			} 
		}
	}
	
	/**
	 * Removes shoots that aren't shown.
	 */
	public void removeShoots() {
		@SuppressWarnings("unchecked")
		ArrayList<Shoot> shootsCheckList = (ArrayList<Shoot>) shoots.clone();
		for(Shoot shoot : shootsCheckList) {
			if (!shoot.isVisible()) {
				shoots.remove(shoot);
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
	public boolean getEpicWeapon() {
		return epicWeapon;
	}
	public void setEpicWeapon(boolean epicWeapon) {
		this.epicWeapon = epicWeapon;
	}


	
}
