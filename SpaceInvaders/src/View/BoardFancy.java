package View;

import java.awt.Color;
import java.util.ArrayList;

import Controller.InputController;
import Model.Bomb;
import Model.Enemies;
import Model.Enemy;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import acm.graphics.GRect;

public class BoardFancy extends GCompound implements View {

	private GRect background;
	private Spaceship player;
	private Enemies enemies;
	private GImage ship = new GImage("Spaceship.png");
	private GImage heart = new GImage("heart.png");
	private GImage alien1 = new GImage("alien1.png");
	private GImage alien2 = new GImage("alien2.png");
	private GImage alien3 = new GImage("alien3.png");
	private GImage sky = new GImage("spaceWithLetter.png");
	private GImage bomb = new GImage("bomb.png");
	private GImage bullet = new GImage("bullet.png");
	private GImage bullet2 = new GImage("bullet2.png");
	private GImage bullet3 = new GImage("bullet3.png");
	private GImage won = new GImage("won.png");
	private GImage gameover = new GImage("gameover.png");
	private int changeImage = 0;

	public void newFrame(Spaceship player, Enemies enemies) {

		this.player = player;
		this.enemies = enemies;

		removeAll();

		drawBackground();
		drawShip();
		drawShoots(player);
		drawBombs();
		drawEnemies();
		drawLife();

		losingScreen();
		winningScreen();

	}

	private void drawBackground() {
		background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER,
				Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}

	public void drawShip() {

		ship.setLocation(player.getX() * Spaceship.MULTIPLIER, player.getY() * Spaceship.MULTIPLIER);
		ship.setSize(62, 46);
		ship.setVisible(true);
		add(ship);
	}

	public void drawEnemies() {
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for (Enemy enemy : enemyList) {
			drawEnemy(enemy);
		}
	}

	public void drawEnemy(Enemy enemy) {
		GImage alien = new GImage(this.alien1.getImage());
		
		switch (enemy.getRow()) {
		case 1:
			alien = new GImage(this.alien2.getImage());
			break;
		case 2:
			alien = new GImage(this.alien3.getImage());
			break;
		default:
			break;
		}
		alien.setLocation(enemy.getX() * Spaceship.MULTIPLIER, enemy.getY() * Spaceship.MULTIPLIER);
		alien.setSize(41, 41);
		add(alien);
	}

	public void drawShoots(Spaceship ship) {
		for (Shoot shoot : ship.shoots) {
			GImage bullet;

			if (changeImage % 3 == 0) {
				bullet = new GImage(this.bullet2.getImage());
				changeImage++;
			} else if (changeImage % 2 == 0) {
				bullet = new GImage(this.bullet3.getImage());
				changeImage++;
			} else {
				bullet = new GImage(this.bullet.getImage());
				changeImage++;
			}

			bullet.setLocation(shoot.getX() * Spaceship.MULTIPLIER + 18, shoot.getY() * Spaceship.MULTIPLIER - 8);
			bullet.setSize(25, 25);
			add(bullet);

		}

	}

	public void drawBombs() {
		for (Bomb bomb : enemies.getBombs()) {
			GImage bomb1 = new GImage(this.bomb.getImage());
			bomb1.setLocation(bomb.getX() * Spaceship.MULTIPLIER + 15, bomb.getY() * Spaceship.MULTIPLIER);
			bomb1.setSize(20, 20);
			add(bomb1);
		}
	}

	public void drawLife() {
		for (int i = 0; i < player.getLife(); i++) {
			GImage heart = new GImage(this.heart.getImage());
			heart.setLocation((player.getBaseWidth() - 4 + i) * Spaceship.MULTIPLIER,
					(player.getBaseHeight() - 1) * Spaceship.MULTIPLIER);
			heart.setSize(30, 30);
			add(heart);
		}
	}

	public void startScreen() {
		GImage sky = new GImage(this.sky.getImage());
		add(sky);
	}

	private void winningScreen() {
		if (player.won()) {
			GImage won = new GImage(this.won.getImage());
			won.setSize(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER, Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
			add(won);
		}

	}

	private void losingScreen() {
		if (player.lost()) {
			GImage gameover = new GImage(this.gameover.getImage());
			gameover.setSize(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER, Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
			add(gameover);
		}

	}

}
