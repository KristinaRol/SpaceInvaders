package View;

import java.awt.Color;
import java.util.ArrayList;

import Model.Enemies;
import Model.Enemy;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Board extends GCompound {
	
	public final static int BASE_WIDTH = 28;
	public final static int BASE_HEIGHT = 14;
	public final static int MULTIPLIER = 50;
	private GRect background;
	Spaceship player;
	
	public Board() {
	}
	
	//draws spaceship as a rectangle
	public void drawShip(Spaceship ship) {
		player = ship;
		drawBackground();
		GRect rect = new GRect(ship.getX() * MULTIPLIER, ship.getY() * MULTIPLIER, MULTIPLIER, MULTIPLIER);
		rect.setFilled(true);
		rect.setColor(Color.GREEN);
		add(rect);
	}
	
	public void drawEnemies(Enemies enemies) {
		
		winningScreen(enemies);
		losingScreen(enemies);
		
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for(Enemy enemy :enemyList ) {
			drawEnemy(enemy);
		}
	}
	
	public void drawEnemy(Enemy enemy) {
		GRect rect = new GRect(enemy.getX() * MULTIPLIER, enemy.getY() * MULTIPLIER, MULTIPLIER, MULTIPLIER);
		rect.setFilled(true);
		rect.setColor(Color.RED);
		add(rect);
	}
	
	private void drawBackground() {
		background = new GRect(BASE_WIDTH * MULTIPLIER, BASE_HEIGHT * MULTIPLIER);
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}
	
	private void winningScreen(Enemies enemies) {
		
		
		if (enemies.areAllDead()) {
			background = new GRect(BASE_WIDTH * MULTIPLIER, BASE_HEIGHT * MULTIPLIER);
			background.setFilled(true);
			background.setColor(Color.GREEN);
			add(background);			
		}
		
	}
	
	private void losingScreen(Enemies enemies) {
		
		if (enemies.killPlayer(player)) {
			background = new GRect(BASE_WIDTH * MULTIPLIER, BASE_HEIGHT * MULTIPLIER);
			background.setFilled(true);
			background.setColor(Color.RED);
			add(background);			
		}
		
	}

	public void drawShoots(Spaceship ship) {
		for(Shoot shoot : ship.shoots) {
			GRect oval = new GRect(shoot.getX() * MULTIPLIER, shoot.getY() * MULTIPLIER, MULTIPLIER, MULTIPLIER);
			oval.setFilled(true);
			oval.setColor(Color.YELLOW);
			add(oval);
		}
	}
}
