package Controller;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Model.Enemies;
import Model.Spaceship;
import View.Board;
import View.BoardFancy;
import View.LHView;
import acm.program.GraphicsProgram;

//the main method which starts the game
@SuppressWarnings("serial")
public class StartUp extends GraphicsProgram {

	InputController inputController;

	public static void main(String[] args) {
		
		
		new StartUp().start();
	}

	 
	public void init() {
        //int delta = getSize().height - getCentralRegionSize().height;
        
        //this.setSize(getSize().width, Board.BASE_HEIGHT * Board.MULTIPLIER + delta);
		
		// create and add the game board
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

		//addKeyListeners();
		inputController.start();
		getGCanvas().addKeyListener(inputController);
		
	}

	@Override
	public void run() {
		super.run();
	}
	


}
