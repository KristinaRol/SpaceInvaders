package Controller;

import java.util.ArrayList;

import Model.Shoot;
import Model.Spaceship;
import View.Board;

public class InputController extends Thread {

	Spaceship spaceship = new Spaceship(350, 350);
	Board board = new Board();
	// [0] is left, [1] is right
	public boolean[] keyPressed = new boolean[2];

	public InputController(Board board) {
		this.board = board;
	}

	// die Dauerschleife
	public void run() {

		while (true) {
			// updated the spaceship the whole time
			board.removeAll();
			board.drawShip(spaceship);
			board.drawShoots(spaceship);
			move();

			try {
				sleep(16);
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
		spaceship.shoot();
	}

	public void move() {
		if (keyPressed[0]) {
			pressedLeft();
		}
		if (keyPressed[1]) {
			pressedRight();
		}
		for (Shoot shoot : spaceship.shoots) {
			if (shoot.isAlive()) {
				shoot.setY(-0.001);
			} else {
				spaceship.shoots.remove(shoot);
			}
		}
	}

}
