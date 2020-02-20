package Model;

import java.util.ArrayList;

public class Enemies {

	private ArrayList<Enemy> enemyList = new ArrayList<>();
	private ArrayList<Bomb> bombList = new ArrayList<>();
	private boolean direction = true;
	

	/**
	 * Creates the enemies.
	 */
	public Enemies(int rows, int columns) {
		int r = 2;

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {

				enemyList.add(new Enemy(3 + col * 2, 0 + row * 2, r));
			}
			r--;
		}
	}

	public ArrayList<Enemy> getEnemmyList() {
		return enemyList;
	}

	public void removeDestroiedEnemies() {
		@SuppressWarnings("unchecked")
		ArrayList<Enemy> enemmyCheckList = (ArrayList<Enemy>) enemyList.clone();
		for (Enemy enemy : enemmyCheckList) {
			// Remove the enemy from the list if it was hit by a shot.
			if (enemy.getHitpoints() == 0) {
				enemyList.remove(enemy);
			}
		}
	}

	public boolean areAllDead() {
		return enemyList.size() == 0;
	}

	/**
	 * Updates the positions of the enemies.
	 * 
	 * @param row The row that is moved.
	 */
	public void move(int row) {
		checkDirection();

		for (Enemy enemy : enemyList) {
			if (enemy.getRow() == row) {
				if (direction) {
					enemy.setX(enemy.getX() - 1);
				} else {
					enemy.setX(enemy.getX() + 1);
				}
			}
		}
	}

	/**
	 * Checks in which direction the enemies should be moving.
	 */
	private void checkDirection() {
		for (Enemy enemy : enemyList) {
			if (enemy.getRow() == 2) {
				if (direction) {
					if (enemy.getX() == 0) {
						direction = false;
						lowerByOne();
					}
				}
				else {
					if (enemy.getX() == Spaceship.BASE_WIDTH - 1) {
						direction = true;
						lowerByOne();
					}
				}
			}
		}
	}
	
	/**
	 * If the enemies are touching a wall, they move down by one.
	 */
	private void lowerByOne() {
		for (Enemy enemy : enemyList) {
			enemy.setY(enemy.getY() + 1);
		}
	}
	
	/**
	 * If a enemy is touching the player.
	 */
	public boolean killPlayer(Spaceship player) {
		
		for (Enemy enemy : enemyList) {
			if (enemy.getX() == player.getX() && enemy.getY() == player.getY()) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	public void removeInvisbleBombs() {
		@SuppressWarnings("unchecked")
		ArrayList<Bomb> bombCheckList = (ArrayList<Bomb>) bombList.clone();
		for(Bomb bomb : bombCheckList) {
			if (!bomb.isVisible()) {
				bombList.remove(bomb);
			}
		}
	}

	public void moveBombs() {
		for(Bomb bomb : bombList) {
			bomb.move();
		}
	}

	/**
	 * Checks if a bomb hits the player or if an enemy is touching the player.
	 */
	public void hitsPlayer(Spaceship player) {
		
		// Checks if an enemy touches the player. Kills player.
		if (killPlayer(player)) {
			player.setLife(0);
			return;
		}
		
		for (Enemy enemy : enemyList) {
			if (enemy.getY() > 13) {
				player.setLife(0);
				return;
			}
		}
		
		// Checks if a bomb touches the player. Removes one life.
		for(Bomb bomb : bombList) {
			if (bomb.getX() == player.getX() && bomb.getY() == player.getY()) {
				player.setLife(player.getLife() - 1);
			}
		}
	}

	// Randomly generates bombs.
	public void createBomb() {
		int rand = (int) (Math.random() * 12);
		
		if (rand == 0) {
			// Decides where the bomb should spawn.
			rand = (int) (Math.random() * enemyList.size());
			if (enemyList.size() > 0) {
				Bomb bomb = new Bomb(enemyList.get(rand).getX() , enemyList.get(rand).getY());
				bombList.add(bomb);				
			}
		}
	}
	
	
	public ArrayList<Bomb> getBombs() {
		return bombList;
	}
	

}
