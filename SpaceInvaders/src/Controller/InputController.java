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
	//Bord board;
	private BoardFancy board;
	//BoardFancy board;
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
			move();
			
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
			if (System.currentTimeMillis() - timeOfLastShoot > 750) {
				spaceship.shoot();
				timeOfLastShoot = System.currentTimeMillis();
			}
		}

		for (Shoot shoot : spaceship.shoots) {
			shoot.setY((int) (shoot.getY() - 1 - spaceship.getVelocityMultiplier()));
			if (shoot.hitsEnemy(enemies)) {
				shoot.setY(-5);
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
