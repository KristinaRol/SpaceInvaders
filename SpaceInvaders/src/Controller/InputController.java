package Controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import Model.Enemies;
import Model.Shoot;
import Model.Spaceship;
import View.Board;
import View.BoardFancy;
import View.LHView;
public class InputController extends Thread implements KeyListener {
	private Enemies enemies;
	private BoardFancy board;
	//private Board board;
	private Spaceship spaceship;
	private LHView lightHouse;
	private int counter = 0;
	private double timeOfLastShoot = System.currentTimeMillis();

	// Hash Map of the pressed buttons.
	private HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>();

	public InputController(BoardFancy board, LHView lightHouse, Spaceship player, Enemies enemies) {

		// The two model classes.
		this.spaceship = player;
		this.enemies = enemies;

		// The light house view.
		this.lightHouse = lightHouse;

		this.board = board;
		keyPressed.put(KeyEvent.VK_LEFT, false);
		keyPressed.put(KeyEvent.VK_RIGHT, false);
		keyPressed.put(KeyEvent.VK_SPACE, false);
		keyPressed.put(-1, false);
	}
	// die Dauerschleife
	public void run() {

		while (true) {
			
			if (spaceship.getStart()) {
				// Counter for some stuff that should only be done once in a while.
				counter = counter % 18;
				// Updating the models.
				enemies.removeDestroiedEnemies();
				enemies.removeInvisbleBombs();
				enemies.moveBombs();
				enemies.createBomb();
				spaceship.shootEnemy(enemies);
				move();
				enemies.hitsPlayer(spaceship);
				spaceship.checkGameState(enemies);
				spaceship.removeExplosions();
				spaceship.removeShoots();
				if (counter % 3 == 0) {
						
					spaceship.nextStateExplosion();				
				}

				if (counter < 9) {
					if (counter % 3 == 0) {
						enemies.move(counter / 3);	
					}
				}

				// Updates the views.
				board.newFrame(spaceship, enemies);
				lightHouse.newFrame(spaceship, enemies);
				counter++;
			} else {
				board.startScreen();
				lightHouse.startScreen();
			}


			// Stop for a certain amount before the next frame.
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	// Calles methods according to the buttons that are pressed.
	public void move() {
		if (keyPressed.get(KeyEvent.VK_LEFT)) {
			spaceship.moveLeft();
		}
		if (keyPressed.get(KeyEvent.VK_RIGHT)) {
			spaceship.moveRight();
		}
		if (keyPressed.get(KeyEvent.VK_SPACE)) {

			// Makes sure that the player can only shoot once every 750ms.
			if (System.currentTimeMillis() - timeOfLastShoot > 750) {
				spaceship.shoot();
				timeOfLastShoot = System.currentTimeMillis();
			}
		}

		

	}

	// Key inputs. Changes the boolean of the hash map.
	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		
		if (spaceship.getStart()) {
			keyPressed.put(e.getKeyCode(), true);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spaceship.setStart();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed.put(e.getKeyCode(), false);
	}
}