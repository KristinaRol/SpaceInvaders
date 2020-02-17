package Controller;

import java.awt.event.KeyEvent;

import View.Board;
import View.BoardFancy;
import acm.program.GraphicsProgram;

//the main method which starts the game
public class StartUp extends GraphicsProgram {

	InputController inputController;

	public static void main(String[] args) {
		
		
		new StartUp().start();
	}

	
	public void init() {
		
		/*
		System.out.println("Frame Size   : " + this.getSize() );
        System.out.println("Frame Insets : " + this.getInsets() );
        System.out.println("Content Size : " + this.getContentPane().getSize() );
     	*/
        
        
        //int delta = getSize().height - getCentralRegionSize().height;
        
        //this.setSize(getSize().width, Board.BASE_HEIGHT * Board.MULTIPLIER + delta);
		
		// create and add the game board
		Board board = new Board();
		//BoardFancy board = new BoardFancy();
		this.setSize(board.getIntWidth(), board.getIntHeight());
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
