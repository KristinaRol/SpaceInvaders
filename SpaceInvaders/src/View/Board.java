package View;

import java.awt.Color;
import java.util.ArrayList;

import Model.Enemies;
import Model.Enemy;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Board extends GCompound implements View {
	
	private GRect background;
	Spaceship player;
	Enemies enemies;
	
	
	public void newFrame(Spaceship player, Enemies enemies) {
		this.player = player;
		this.enemies = enemies;
		
		removeAll();
		
		drawBackground();
		drawShip();
		drawShoots(player);
		drawEnemies();
		winningScreen();
		losingScreen();
	}

	
	private void drawBackground() {
		background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER, Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}
	
	
	//draws spaceship as a rectangle
	public void drawShip() {
		GRect rect = new GRect(player.getX() * Spaceship.MULTIPLIER, player.getY() * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
		rect.setFilled(true);
		rect.setColor(Color.GREEN);
		add(rect);
	}
	
	public void drawEnemies() {
		
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for(Enemy enemy :enemyList ) {
			drawEnemy(enemy);
		}
	}
	
	public void drawEnemy(Enemy enemy) {
		GRect rect = new GRect(enemy.getX() * Spaceship.MULTIPLIER, enemy.getY() * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
		rect.setFilled(true);
		rect.setColor(Color.RED);
		add(rect);
	}
	
	private void winningScreen() {
		
		if (enemies.areAllDead()) {
			background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER, Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
			background.setFilled(true);
			background.setColor(Color.GREEN);
			add(background);			
		}
		
	}
	
	private void losingScreen() {
		
		if (enemies.killPlayer(player)) {
			background = new GRect(Spaceship.BASE_WIDTH * Spaceship.MULTIPLIER, Spaceship.BASE_HEIGHT * Spaceship.MULTIPLIER);
			background.setFilled(true);
			background.setColor(Color.RED);
			add(background);			
		}
		
	}

	public void drawShoots(Spaceship ship) {
		for(Shoot shoot : ship.shoots) {
			GRect oval = new GRect(shoot.getX() * Spaceship.MULTIPLIER, shoot.getY() * Spaceship.MULTIPLIER, Spaceship.MULTIPLIER, Spaceship.MULTIPLIER);
			oval.setFilled(true);
			oval.setColor(Color.YELLOW);
			add(oval);
		}
	}
	
	
}
