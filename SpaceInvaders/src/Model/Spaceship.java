package Model;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Controller.InputController;

public class Spaceship {

	public final static int BASE_WIDTH = 28;
	public final static int BASE_HEIGHT = 14;
	public final static int MULTIPLIER = 50;

	private int x;
	private int y;
	private int life;
	private boolean epicWeapon = false;
	private boolean changedState = false;

	private int winningExplosionState = 0;
	private int winningExplosionX;
	private int winningExplosionY;

	// -1 = lose, 0 = normal, 1 = win.
	public int winLose = 0;
	private boolean start = false;

	public ArrayList<Shoot> shoots = new ArrayList<>();
	public ArrayList<Explosion> explosionList = new ArrayList<>();

	public Spaceship() {
		this.x = 13;
		this.y = 12;
		this.life = 3;
	}

	public void shoot() {
		play("shoot.wav");
		Shoot shoot = new Shoot(x, y, Shoot.DIRECTION_UP);
		shoots.add(shoot);
	}

	private void play(String filename) {
		try {
			// Open an audio input stream.
			URL url = this.getClass().getClassLoader().getResource(filename);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
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

			// Places the shoot at a position where it will be removed, if the shoot hits an
			// enemy
			if (shoot.hitsEnemy(enemies)) {

				winningExplosionX = shoot.getX();
				winningExplosionY = shoot.getY();

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
	 * Checks if the player already lost or won. and if lower enemy row doesn't
	 * exist, player gets epic weapon.
	 */
	public void checkGameState(Enemies enemies) {

		if (enemies.areAllDead()) {
			if (winLose != -1) {
				winLose = 1;
				if (winningExplosionState < 50) {
					winningExplosionState++;
				}
				
				InputController.state = MusicState.Win;
				if (!changedState) {
					InputController.changed = true;
					changedState = true;
				}
			}
		} else if (life <= 0) {
			if (winLose != 1) {
				winLose = -1;
				InputController.state = MusicState.Death;
				if (!changedState) {
					InputController.changed = true;
					changedState = true;
				}
			}
		}

		epicWeapon = true;
		for (Enemy enemy : enemies.getEnemmyList()) {
			if (enemy.getRow() == 0) {
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
		for (Explosion explosion : explosionList) {
			explosion.nextState();
		}
	}

	/**
	 * Removes explosions that are already shown long enough.
	 */
	public void removeExplosions() {
		@SuppressWarnings("unchecked")
		ArrayList<Explosion> explosionCheckList = (ArrayList<Explosion>) explosionList.clone();
		for (Explosion explosion : explosionCheckList) {
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
		for (Shoot shoot : shootsCheckList) {
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
		return BASE_WIDTH - 1;
	}

	public boolean getEpicWeapon() {
		return epicWeapon;
	}

	public void setEpicWeapon(boolean epicWeapon) {
		this.epicWeapon = epicWeapon;
	}

	public int getWinningExplosionX() {
		return winningExplosionX;
	}

	public int getWinningExplosionY() {
		return winningExplosionY;
	}

	public int getWinningExplosionState() {
		return winningExplosionState;
	}

}