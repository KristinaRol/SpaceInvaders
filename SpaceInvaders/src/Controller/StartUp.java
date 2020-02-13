package Controller;

import java.awt.event.KeyEvent;

import View.Board;
import acm.program.GraphicsProgram;

//the main method which starts the game
public class StartUp extends GraphicsProgram {
	
	InputController inputController;

	public static void main(String[] args) {
		new StartUp().start();
	}

	public void init() {

		//create and add the game board
		Board board = new Board();
		this.inputController = new InputController(board);
		add(board);

		setResizable(false);
		setLocationRelativeTo(null);

		addKeyListeners();
		inputController.start();
	}

	//if key pressed methods in initController move the ship
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			inputController.pressedLeft();
			break;
		case KeyEvent.VK_RIGHT:
			inputController.pressedRight();
			break;
		}
	}

	@Override
	public void run() {
		init();
		super.run();
	}

}
