package Controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import Model.Enemies;
import Model.Shoot;
import Model.Spaceship;
import View.Board;
import View.BoardFancy;

public class InputController extends Thread {

	Enemies enemies = new Enemies(3, 13);
	Board board;
	//BoardFancy board;
	Spaceship spaceship = new Spaceship(board); 
	private int counter = 0;
	double timeOfLastShoot = System.currentTimeMillis();
	public HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>();

	public InputController(Board board) {
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
			board.removeAll();
			board.drawShip(spaceship);
			board.drawShoots(spaceship);
			board.drawEnemies(enemies);
			
			counter++;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pressedLeft() {
		spaceship.setX(spaceship.getX() - 1 -board.getVelocityMultiplier());
	}

	public void pressedRight() {
		spaceship.setX(spaceship.getX() + 1 +board.getVelocityMultiplier());
	}

	public void pressedSpace() {
		if (System.currentTimeMillis() - timeOfLastShoot > 750) {
			spaceship.shoot();
			timeOfLastShoot = System.currentTimeMillis();
		}
	}

	public void move() {
		if (keyPressed.get(KeyEvent.VK_LEFT)) {
			if (spaceship.getX() > 0) {
				pressedLeft();				
			}
		}
		if (keyPressed.get(KeyEvent.VK_RIGHT)) {
			if (spaceship.getX() < board.getMaxXFromShip()) {
				pressedRight();				
			}
		}
		if (keyPressed.get(KeyEvent.VK_SPACE)) {
			pressedSpace();
		}

		for (Shoot shoot : spaceship.shoots) {
			shoot.setY(shoot.getY() - 1 -board.getVelocityMultiplier());
			if (shoot.hitsEnemy(enemies, board)) {
				shoot.setY(-5);
			}
		}
		// i don't know what happens here, but the shoots which aren't visible anymore are filtered out
		spaceship.shoots = (ArrayList<Shoot>) spaceship.shoots.stream().filter(shoot -> shoot.isAlive())
				.collect(Collectors.toList());

	}

}
