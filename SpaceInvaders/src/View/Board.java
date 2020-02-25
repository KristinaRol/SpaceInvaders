package View;

import java.awt.Color;
import java.util.ArrayList;

import Model.Bomb;
import Model.Enemies;
import Model.Enemy;
import Model.Explosion;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Board extends GCompound implements View {

	private GRect background;
	private Spaceship player;
	private Enemies enemies;

	public void newFrame(Spaceship player, Enemies enemies) {
		this.player = player;
		this.enemies = enemies;

		removeAll();

		drawBackground();
		drawExplosions();
		drawShip();
		drawShoots(player);
		drawBombs();
		drawEnemies();
		drawLife();
		winningScreen();
		losingScreen();
	}

	private void drawBackground() {
		background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER,
				Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}

	
	private void drawExplosions() {
		for (Explosion explosion : player.getExplosions()) {
			drawExplosion(explosion.getX(), explosion.getY(), explosion.getState());
		}
	}
	
	private void drawExplosion(int x, int y, int state) {
		GRect rect1 = new GRect(0,0);
		GRect rect2 = new GRect(0,0);
		switch (state) {
		case 0:
			rect1 = new GRect((x - 1)  * Spaceship.MULTIPLIER, y * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER * 3, Spaceship.MULTIPLIER);
			rect2 = new GRect(x  * Spaceship.MULTIPLIER, (y - 1) * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER, Spaceship.MULTIPLIER * 3);
			rect1.setColor(Color.YELLOW);
			rect2.setColor(Color.YELLOW);
			break;
		case 1:
			rect1 = new GRect((x - 1)  * Spaceship.MULTIPLIER, (y - 1) * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER * 3, Spaceship.MULTIPLIER * 3);
			rect1.setColor(Color.ORANGE);
			break;
		case 2:
			rect1 = new GRect((x - 1)  * Spaceship.MULTIPLIER, (y - 1) * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER * 3, Spaceship.MULTIPLIER * 3);
			rect2 = new GRect((x - 2)  * Spaceship.MULTIPLIER, y * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER * 5, Spaceship.MULTIPLIER);
			GRect rect3 = new GRect(x  * Spaceship.MULTIPLIER, (y - 2) * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER, Spaceship.MULTIPLIER * 5);
			rect1.setColor(new Color(153, 15, 15));
			rect2.setColor(new Color(153, 15, 15));
			rect3.setColor(new Color(153, 15, 15));
			rect3.setFilled(true);
			add(rect3);
			break;
		}
		rect1.setFilled(true);
		rect2.setFilled(true);
		add(rect1);
		add(rect2);
	}

	
	// draws spaceship as a rectangle
	public void drawShip() {
		GRect rect = new GRect(player.getX() * Spaceship.MULTIPLIER, player.getY() * Spaceship.MULTIPLIER,
				Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
		rect.setFilled(true);
		rect.setColor(Color.GREEN);
		add(rect);
	}

	public void drawEnemies() {

		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for (Enemy enemy : enemyList) {
			drawEnemy(enemy);
		}
	}

	public void drawEnemy(Enemy enemy) {
		GRect rect = new GRect(enemy.getX() * Spaceship.MULTIPLIER, enemy.getY() * Spaceship.MULTIPLIER,
				Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
		rect.setFilled(true);
		rect.setColor(Color.RED);
		add(rect);
	}

	public void drawShoots(Spaceship ship) {
		for (Shoot shoot : ship.shoots) {
			GRect oval = new GRect(shoot.getX() * Spaceship.MULTIPLIER, shoot.getY() * Spaceship.MULTIPLIER,
					Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
			oval.setFilled(true);
			oval.setColor(Color.YELLOW);
			add(oval);
		}
	}

	public void drawBombs() {
		for (Bomb bomb : enemies.getBombs()) {
			GRect bombRect = new GRect(bomb.getX() * Spaceship.MULTIPLIER, bomb.getY() * Spaceship.MULTIPLIER,
					Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
			bombRect.setFilled(true);
			bombRect.setColor(Color.ORANGE);
			add(bombRect);
		}
	}

	public void drawLife() {
		for (int i = 0; i < player.getLife(); i++) {
			GRect heart = new GRect((player.getBaseWidth() - 4 + i) * Spaceship.MULTIPLIER,
					(player.getBaseHeight() - 1) * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
			heart.setFilled(true);
			heart.setFillColor(Color.RED);
			add(heart);
		}
	}

	private void winningScreen() {

		if (player.won()) {
			background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER,
					Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
			background.setFilled(true);
			background.setColor(Color.GREEN);
			add(background);
		}

	}

	private void losingScreen() {

		if (player.lost()) {
			background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER,
					Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
			background.setFilled(true);
			background.setColor(Color.RED);
			add(background);
		}

	}

}
