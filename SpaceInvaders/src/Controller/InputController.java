package Controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import Model.Enemies;
import Model.Shoot;
import Model.Spaceship;
import View.Board;

public class InputController extends Thread {

	Spaceship spaceship = new Spaceship(350, 350);
	Enemies enemies = new Enemies(3, 7);
	Board board;
	double timeOfLastShoot = System.currentTimeMillis();
	// [0] is left, [1] is right, [2] is space
	//public boolean[] keyPressed = new boolean[3];
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
			// updated the spaceship the whole time
			move();
			board.removeAll();
			board.drawShip(spaceship);
			board.drawShoots(spaceship);
			board.drawEnemies(enemies);

			try {
				sleep( 16 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pressedLeft() {
		spaceship.setX(spaceship.getX() - 12);
	}

	public void pressedRight() {
		spaceship.setX(spaceship.getX() + 12);
	}

	public void pressedSpace() {
		if (System.currentTimeMillis() - timeOfLastShoot > 750) {
			spaceship.shoot();
			timeOfLastShoot = System.currentTimeMillis();
		}
	}

	public void move() {
		if (keyPressed.get(KeyEvent.VK_LEFT)) {
			pressedLeft();
		}
		if (keyPressed.get(KeyEvent.VK_RIGHT)) {
			pressedRight();
		}
		if (keyPressed.get(KeyEvent.VK_SPACE)) {
			pressedSpace();
		}

		for (Shoot shoot : spaceship.shoots) {
			shoot.setY(shoot.getY() - 1.5);
		}
		// i don't know what happens here, but the shoots which aren't visible anymore are filtered out
		spaceship.shoots = (ArrayList<Shoot>) spaceship.shoots.stream().filter(shoot -> shoot.isAlive())
				.collect(Collectors.toList());

	}

}
