package Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Model.Enemies;
import Model.Spaceship;
import View.Board;
import View.BoardFancy;
import View.LHView;
import acm.program.GraphicsProgram;

//the main method which starts the game
public class StartUp extends GraphicsProgram {

	InputController inputController;

	public static void main(String[] args) {
		
		
		new StartUp().start();
	}

	
	public void init() {
        //int delta = getSize().height - getCentralRegionSize().height;
        
        //this.setSize(getSize().width, Board.BASE_HEIGHT * Board.MULTIPLIER + delta);
		
		// Creates and adds the game board.
		//Board board = new Board();
		BoardFancy board = new BoardFancy();
		LHView lightHouse = new LHView();
		
		lightHouse.initDisplay();
		
		Spaceship player = new Spaceship();
		Enemies enemies = new Enemies(3, 13);
		this.setSize(player.getIntWidth(), player.getIntHeight());
		this.inputController = new InputController(board, lightHouse, player, enemies);
		add(board);
		
		setResizable(false);
		setLocationRelativeTo(null);

		inputController.start();
		
		// Sets the Key Listener.
		getGCanvas().addKeyListener(inputController);
		
		
		
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed");
                lightHouse.close();
                e.getWindow().dispose();
            }
        });
		
	}

	@Override
	public void run() {
		super.run();
	}

}
