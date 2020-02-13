package Controller;

import Model.Spaceship;
import View.Board;

public class InputController extends Thread {

	Spaceship spaceship = new Spaceship(10,200);
	Board board = new Board();
	
	public InputController(Board board) {
		this.board = board;
	}
	
	//die Dauerschleife
	public void run() {
		
		while (true) {
			//updated the spaceship the whole time
			board.removeAll();
			board.drawShip(spaceship);
		
			try {
				sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void pressedLeft() {
		spaceship.setX(spaceship.getX()-10);
	}

	public void pressedRight() {
		spaceship.setX(spaceship.getX()+10);
	}
}
