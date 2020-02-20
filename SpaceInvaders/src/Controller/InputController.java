package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import Model.Enemies;
import Model.Shoot;
import Model.Spaceship;
import View.Board;
import View.BoardFancy;
import View.LHView;

public class InputController extends Thread implements KeyListener {

	private Enemies enemies;
	private BoardFancy board;
	//private Board board;
	private Spaceship spaceship;
	private LHView lightHouse;
	private int counter = 0;
	private double timeOfLastShoot = System.currentTimeMillis();
	private HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>();

	public InputController(BoardFancy board, LHView lightHouse, Spaceship player, Enemies enemies) {
		
		this.spaceship = player;
		this.enemies = enemies;
		this.lightHouse = lightHouse;
		
		this.board = board;
		keyPressed.put(KeyEvent.VK_LEFT, false);
		keyPressed.put(KeyEvent.VK_RIGHT, false);
		keyPressed.put(KeyEvent.VK_SPACE, false);
		keyPressed.put(-1, false);
	}

	// die Dauerschleife
	public void run() {

		while (true) {
			counter = counter % 18;
			// updated the spaceship the whole time
			enemies.removeDestroiedEnemies();
			enemies.removeInvisbleBombs();
			enemies.moveBombs();
			enemies.createBomb();
			spaceship.shootEnemy(enemies);
			move();
			enemies.hitsPlayer(spaceship);
			spaceship.checkGameState(enemies);
			spaceship.removeExplosions();
			if (counter % 3 == 0) {
				spaceship.nextStateExplosion();				
			}
			
			if (counter < 9) {
				if (counter % 3 == 0) {
					enemies.move(counter / 3);	
				}
			}
			
			board.newFrame(spaceship, enemies);
			//lightHouse.newFrame(spaceship, enemies);
			counter++;
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	public void move() {
		if (keyPressed.get(KeyEvent.VK_LEFT)) {
			spaceship.moveLeft();
		}
		if (keyPressed.get(KeyEvent.VK_RIGHT)) {
			spaceship.moveRight();
		}
		if (keyPressed.get(KeyEvent.VK_SPACE)) {
			board.playingCurrently = true;
			if (System.currentTimeMillis() - timeOfLastShoot > 750) {
				spaceship.shoot();
				timeOfLastShoot = System.currentTimeMillis();
			}
		}

		// i don't know what happens here, but the shoots which aren't visible anymore are filtered out
		spaceship.shoots = (ArrayList<Shoot>) spaceship.shoots.stream().filter(shoot -> shoot.isAlive())
				.collect(Collectors.toList());

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		keyPressed.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed.put(e.getKeyCode(), false);
	}

}
