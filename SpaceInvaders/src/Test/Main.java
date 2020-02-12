package Test;

import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram {

	// Constants that define the size of the views
	private double WIDTH = 400;
	private double HEIGHT = 400;
	Model model = new Model();
	View view = new View(WIDTH, HEIGHT);

	public static void main(String[] args) {
		new Main().start();
	}

	
	public void init() {

		addKeyListeners();

		// Create a model
		Model model = new Model();

		// Create graph view
		View view = new View(WIDTH, HEIGHT);
		add(view);

		model.addShip(view);

		// Create controller
		Controller controller = new Controller();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			model.ship.moveL();
			model.addShip(view);
			break;
		case KeyEvent.VK_RIGHT:
			model.ship.moveR();
			model.addShip(view);
			break;
		}
	}
}