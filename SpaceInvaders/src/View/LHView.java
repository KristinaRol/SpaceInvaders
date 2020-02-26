package View;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Model.Bomb;
import Model.Enemies;
import Model.Enemy;
import Model.Explosion;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GImage;
import acm.util.RandomGenerator;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;

public class LHView implements View {

	private Spaceship player;
	private Enemies enemies;
	private GImage loose = new GImage("loose.png");
	private GImage start1 = new GImage("start1.png");
	private GImage start2 = new GImage("start2.png");
	private GImage pixelShip = new GImage("pixelShip.png");
	private int moveImage = 0;
	private int border = 0;
	private int random = 0;

	// Array of the pixels that are shown on the light house.
	// One pixel consists of an red, green and blue value.
	private byte[] data = new byte[28 * 14 * 3];
	private LighthouseDisplay display = null;

	/**
	 * Updates the view.
	 */
	public void newFrame(Spaceship player, Enemies enemies) {
		this.player = player;
		this.enemies = enemies;

		// Clears the array.
		data = new byte[28 * 14 * 3];

		if (player.winLose != 1) {
			// Draws the player.
			insertColorInData(player.getX(), player.getY(), Color.GREEN);
			// Draws the other stuff.
			drawLife();
			drawEnemies();
			drawShoot();
			drawBombs();
		}

		drawExplosions();

		drawWinningExplosion();

		// player.winLose = -1;
		looseScreen();
		//player.winLose = 1;
		winningScreen();

		sendToDisplay();
	}

	public void drawEnemies() {

		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for (Enemy enemy : enemyList) {
			drawEnemy(enemy);
		}
	}

	public void drawEnemy(Enemy enemy) {
		insertColorInData(enemy.getX(), enemy.getY(), Color.RED);
	}

	private void drawShoot() {
		for (Shoot shoot : player.shoots) {
			insertColorInData(shoot.getX(), shoot.getY(), Color.YELLOW);

		}
	}

	public void drawBombs() {
		for (Bomb bomb : enemies.getBombs()) {
			insertColorInData(bomb.getX(), bomb.getY(), Color.MAGENTA);
		}
	}

	public void drawLife() {
		for (int i = 0; i < player.getLife(); i++) {
			insertColorInData(player.getBaseWidth() - 4 + i, player.getBaseHeight() - 1, Color.RED);
		}
	}

	private void drawExplosions() {
		for (Explosion explosion : player.explosionList) {
			drawExplosion(explosion);
		}
	}

	public void startScreen() {
		int[][] alien1 = start1.getPixelArray();
		int[][] alien2 = start2.getPixelArray();
		Color color1;
		Color color2;
		data = new byte[28 * 14 * 3];

		for (int row = 0; row < alien1.length; row++) {
			for (int col = 0; col < alien1[0].length; col++) {
				// takes the color of the pixel
				color1 = new Color(GImage.getRed(alien1[row][col]), GImage.getGreen(alien1[row][col]),
						GImage.getBlue(alien1[row][col]));
				color2 = new Color(GImage.getRed(alien2[row][col]), GImage.getGreen(alien2[row][col]),
						GImage.getBlue(alien2[row][col]));

				if (moveImage % 8 < 4) {
					// alien1
					insertColorInData(col, row, color1);
				} else {
					// alien2
					insertColorInData(col, row, color2);
				}
			}
		}
		outerBorder();
		moveImage++;
		sendToDisplay();
	}

	private void outerBorder() {
		// links
		insertColorInData(0, 13 - border, Color.WHITE);
		insertColorInData(0, 13 - ((border + 7) % 13), Color.YELLOW);
		// rechts
		insertColorInData(27, border, Color.WHITE);
		insertColorInData(27, (border + 7) % 13, Color.YELLOW);
		// oben
		insertColorInData(border * 2, 0, Color.WHITE);
		insertColorInData(((border + 7) % 13) * 2, 0, Color.YELLOW);
		// unten
		insertColorInData(27 - border * 2, 13, Color.WHITE);
		insertColorInData(27 - ((border + 7) % 13) * 2, 13, Color.YELLOW);
		border++;
		border %= 13;
	}

	private void winningScreen() {
		if (player.won()) {
			blinkingBorder();
			drawFirework();
			drawFlyingShip();
		}
	}

	private void drawFirework() {
		Random rand = new Random();
		RandomGenerator rng = RandomGenerator.getInstance();
		if (moveImage % 14 == 0) {
			// Obtain a number between [0 - 27].
			random = rand.nextInt(14 * 28);
		}
		drawCircle(random / 14, random % 14, (moveImage % 14) / 2, rng.nextColor());
		drawCircle(random / 14, random % 14, (moveImage % 14) / 2 - 1, Color.BLACK);
		drawCircle(random / 14, random % 14, (moveImage % 14) / 2 - 2, rng.nextColor());
		drawCircle(random / 14, random % 14, (moveImage % 14) / 2 - 3, Color.BLACK);
	}

	private void drawFlyingShip() {
		int[][] ship = pixelShip.getPixelArray();
		Color color;
		for (int row = 0; row < ship.length; row++) {
			for (int col = 0; col < ship[0].length; col++) {
				color = new Color(GImage.getRed(ship[row][col]), GImage.getGreen(ship[row][col]),
						GImage.getBlue(ship[row][col]));
				insertColorInData(col + 10, ((row - moveImage) % 14) + 13, color);
			}
		}
		if (moveImage == 14 * 14 * 14) {
			moveImage = 0;
		} else {
			moveImage++;
		}
		// moveImage++;
		// moveImage%=14;
	}

	private void blinkingBorder() {
		border %= 2;
		for (int i = border; i < 14; i += 2) {
			// rechts & links
			insertColorInData(0, i, Color.WHITE);
			insertColorInData(27, i, Color.WHITE);
			// oben & unten
			insertColorInData(i * 2, 0, Color.WHITE);
			insertColorInData(i * 2 + 1, 13, Color.WHITE);
		}
		border++;
		border %= 2;
	}

	private void looseScreen() {

		if (player.lost()) {
			int[][] pixel = loose.getPixelArray();
			Color color;

			for (int row = 0; row < pixel.length; row++) {
				for (int col = 0; col < pixel[0].length; col++) {
					// takes the color of the pixel
					color = new Color(GImage.getRed(pixel[row][col]), GImage.getGreen(pixel[row][col]),
							GImage.getBlue(pixel[row][col]));
					if (color.getAlpha() > 50) {
						// upper image which rotates right
						insertColorInData((col + moveImage) % 28, row, color);
						// lower image which rotates left
						insertColorInData(((col - moveImage) % 28) + 27, row + 7, color);
					}
				}
			}
			if (moveImage == 28 * 28 * 28) {
				moveImage = 0;
			} else {
				moveImage++;
			}
		}
	}

	private void drawExplosion(Explosion explosion) {

		insertColorInData(explosion.getX(), explosion.getY(), Color.YELLOW);

		if (explosion.getState() > 0) {
			insertColorInData(explosion.getX() - 1, explosion.getY() - 1, Color.ORANGE);
			insertColorInData(explosion.getX() - 1, explosion.getY(), Color.ORANGE);
			insertColorInData(explosion.getX() - 1, explosion.getY() + 1, Color.ORANGE);
			insertColorInData(explosion.getX(), explosion.getY() - 1, Color.ORANGE);
			insertColorInData(explosion.getX(), explosion.getY() + 1, Color.ORANGE);
			insertColorInData(explosion.getX() + 1, explosion.getY() - 1, Color.ORANGE);
			insertColorInData(explosion.getX() + 1, explosion.getY(), Color.ORANGE);
			insertColorInData(explosion.getX() + 1, explosion.getY() + 1, Color.ORANGE);
		}

		if (explosion.getState() > 1) {
			insertColorInData(explosion.getX() - 1, explosion.getY() - 1, Color.RED);
			insertColorInData(explosion.getX() - 1, explosion.getY(), Color.RED);
			insertColorInData(explosion.getX() - 1, explosion.getY() + 1, Color.RED);
			insertColorInData(explosion.getX(), explosion.getY() - 1, Color.RED);
			insertColorInData(explosion.getX(), explosion.getY() + 1, Color.RED);
			insertColorInData(explosion.getX() + 1, explosion.getY() - 1, Color.RED);
			insertColorInData(explosion.getX() + 1, explosion.getY(), Color.RED);
			insertColorInData(explosion.getX() + 1, explosion.getY() + 1, Color.RED);
		}

	}

	private void drawWinningExplosion() {
		if (player.won()) {
			for (int i = 0; i < 3; i++) {
				drawCircle(player.getWinningExplosionX(), player.getWinningExplosionY(),
						player.getWinningExplosionState() - (i * 6), Color.RED);
				drawCircle(player.getWinningExplosionX(), player.getWinningExplosionY(),
						player.getWinningExplosionState() - (i * 6) - 3, Color.BLACK);
			}
		}
	}

	private void drawCircle(int x, int y, int radius, Color color) {
		for (int row = 0; row < 14; row++) {
			for (int col = 0; col < 28; col++) {
				int deltaX = col - x;
				int deltaY = row - y;
				if (radius > Math.sqrt(deltaX * deltaX + deltaY * deltaY)) {
					insertColorInData(col, row, color);
				}
			}
		}
	}

	/**
	 * Sends the data array to the light house.
	 */
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

	/**
	 * Important method that works like an adapter to easily add pixels to the data
	 * array.
	 */
	private void insertColorInData(int x, int y, Color color) {
		if (x >= 0 && x <= 27 && y >= 0 && y <= 13) {
			// Adds the red pixel.
			data[(x * 3) + (y * 28 * 3)] = (byte) color.getRed();
			// ... the green.
			data[1 + (x * 3) + (y * 28 * 3)] = (byte) color.getGreen();
			// ... the blue.
			data[2 + (x * 3) + (y * 28 * 3)] = (byte) color.getBlue();
		}
	}

	/**
	 * Have to be called once in the startUp class to initialize the light house
	 * display.
	 */
	public void initDisplay() {
		// Try connecting to the display
		try {
			display = LighthouseDisplay.getDisplay();
			display.setUsername("stu215165");
			display.setToken("API-TOK_ZkUz-b+sQ-047u-Ofsz-L2b7");
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void close() {
		display.close();
	}
}
