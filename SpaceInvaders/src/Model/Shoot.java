package Model;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
						play("bomb.wav");
						enemy.remove();
						return true;
					}
				}
			}
		return false;
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
	
	public boolean  isVisible() {
		if (x < 0 || x > Spaceship.BASE_WIDTH || y < -1) {
			return false;
		}
		
		return true;
	}
	
}