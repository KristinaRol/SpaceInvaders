package Controller;

import java.awt.event.KeyEvent;

import Model.Spaceship;
import View.Board;
import acm.program.GraphicsProgram;

//the main method which starts the game
public class StartUp extends GraphicsProgram {

	InputController inputController;

	public static void main(String[] args) {
		
		
		new StartUp().start();
	}

	
	@SuppressWarnings("deprecation") // Because of resize.
	public void init() {
		
		this.resize(Board.WIDTH, Board.WIDTH);
		
		// create and add the game board
		Board board = new Board();
		this.inputController = new InputController(board);
		add(board);

		setResizable(false);
		setLocationRelativeTo(null);

		addKeyListeners();
		inputController.start();
	}

	// if key pressed methods in initController move the ship
	public void keyPressed(KeyEvent e) {
		inputController.keyPressed.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputController.keyPressed.put(e.getKeyCode(), false);
		}
	

	@Override
	public void run() {
		super.run();
	}

}
