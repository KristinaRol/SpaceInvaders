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
	private GImage img = new GImage("Spaceship.png");
	private GImage heart = new GImage("heart.png");
	private GImage alien = new GImage("alien.png");
	private GImage sky = new GImage("spaceWithLetter.png");
	private GImage bomb = new GImage("bomb.png");
	private GImage bullet = new GImage("bullet.png");
	private GImage bullet2 = new GImage("bullet2.png");
	private GImage bullet3 = new GImage("bullet3.png");
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

		img.setLocation(player.getX() * Spaceship.MULTIPLIER, player.getY() * Spaceship.MULTIPLIER);
		img.setSize(60, 40);
		img.setVisible(true);
		add(img);
	}

	public void drawEnemies() {
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for (Enemy enemy : enemyList) {
			drawEnemy(enemy);
		}
	}

	public void drawEnemy(Enemy enemy) {
		GImage alien = new GImage(this.alien.getImage());
		alien.setLocation(enemy.getX() * Spaceship.MULTIPLIER, enemy.getY() * Spaceship.MULTIPLIER);
		alien.setSize(35, 20);
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

			bullet.setLocation(shoot.getX() * Spaceship.MULTIPLIER + 16, shoot.getY() * Spaceship.MULTIPLIER + 16);
			bullet.setSize(25, 25);
			add(bullet);

		}

	}

	public void drawBombs() {
		for (Bomb bomb : enemies.getBombs()) {
			GImage bomb1 = new GImage(this.bomb.getImage());
			bomb1.setLocation(bomb.getX() * Spaceship.MULTIPLIER, bomb.getY() * Spaceship.MULTIPLIER);
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
		GLabel start = new GLabel("Press SCPACE to start", Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER / 2 - 180,
				Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER / 2);
		start.setFont("arial-36");
		start.setColor(Color.WHITE);
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
