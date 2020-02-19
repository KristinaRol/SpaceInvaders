package View;

import java.awt.Color;
import java.util.ArrayList;

import Model.Enemies;
import Model.Enemy;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class BoardFancy extends GCompound implements View {
	
	private GRect background;
	Spaceship player;
	Enemies enemies;
	GImage img = new GImage("Spaceship.png");
	GImage heart = new GImage("heart.png");
	GImage alien = new GImage("alien.png");
	
	
	
	public void newFrame(Spaceship player, Enemies enemies) {
		this.player = player;
		this.enemies = enemies;
		
		removeAll();
		
		drawBackground();
		drawShip();
		drawShoots(player);
		drawEnemies();
		drawLife();
	}
	
	
	

	private void drawBackground() {
		background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER, Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}
	
	//draws spaceship as a rectangle
	public void drawShip() {
		
		img.setLocation(player.getX() * Spaceship.MULTIPLIER, player.getY() * Spaceship.MULTIPLIER);
		img.setSize(Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
		img.setVisible(true);
		add(img);
	}
	
	public void drawEnemies() {
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for(Enemy enemy :enemyList ) {
			drawEnemy(enemy);
		}
	}
	
	public void drawEnemy(Enemy enemy) {
		
		GImage alien = new GImage(this.alien.getImage());
		alien.setLocation(enemy.getX() * Spaceship.MULTIPLIER, enemy.getY() * Spaceship.MULTIPLIER);
		alien.setSize(Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
		add(alien);
	}

	public void drawShoots(Spaceship ship) {
		for(Shoot shoot : ship.shoots) {
			GOval oval = new GOval(shoot.getX() * Spaceship.MULTIPLIER + Spaceship.MULTIPLIER / 2, shoot.getY() * Spaceship.MULTIPLIER + Spaceship.MULTIPLIER / 2 ,10,10);
			oval.setFilled(true);
			oval.setColor(Color.WHITE);
			add(oval);
		}
	}

	public void drawLife() {
		for(int i = 0; i < player.getLife(); i++) {
		GImage heart = new GImage(this.heart.getImage());
		heart.setLocation((player.getBaseWidth() - 4 + i) * Spaceship.MULTIPLIER, (player.getBaseHeight() - 1) * Spaceship.MULTIPLIER);
		add(heart);
		}
		
	}
}

