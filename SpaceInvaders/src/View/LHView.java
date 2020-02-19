package View;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import Model.Bomb;
import Model.Enemies;
import Model.Enemy;
import Model.Shoot;
import Model.Spaceship;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;

public class LHView implements View{

	private Spaceship player;
	private Enemies enemies;
	
	private byte[] data = new byte[28*14*3];
	private LighthouseDisplay display = null;
	
	
	@Override
	public void newFrame(Spaceship player, Enemies enemies) {
		this.player = player;
		this.enemies = enemies;
		
		data = new byte[28*14*3];

		insertColorInData(player.getX(), player.getY(), Color.GREEN);
		drawEnemies();
		drawShoot();
		drawBombs();
		
		sendToDisplay();
	}

	
	public void drawEnemies() {
		
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for(Enemy enemy :enemyList ) {
			drawEnemy(enemy);
		}
	}
	
	public void drawEnemy(Enemy enemy) {
		insertColorInData(enemy.getX(), enemy.getY(), Color.RED);
	}
	
	
	private void drawShoot() {
		for(Shoot shoot : player.shoots) {
			insertColorInData(shoot.getX(), shoot.getY(), Color.YELLOW);
			
		}
	}
	
	
	public void drawBombs() {
		for(Bomb bomb : enemies.getBombs()) {
			insertColorInData(bomb.getX(), bomb.getY(), Color.ORANGE);
		}
	}
	
	

	private void sendToDisplay() {
		// Send data to the display
		try {
			// This array contains for every window (14 rows, 28 columns) three
			// bytes that define the red, green, and blue component of the color
			// to be shown in that window. See documentation of LighthouseDisplay's
			// send(...) method.
			
			display.sendImage(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void insertColorInData(int x, int y, Color color) {
		data[x + (y * 28)] = (byte) color.getRed();
		data[1 + x + (y * 28)] = (byte) color.getGreen();
		data[2 + x + (y * 28)] = (byte) color.getBlue();
	}
	
	
	

	
	public void initDisplay() {
		// Try connecting to the display
		try {
			display = LighthouseDisplay.getDisplay();
		    display.setUsername("Jannis");
		    display.setToken("API-TOK_9h+6-TgOv-47+v-F2kx-yPZS");
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
