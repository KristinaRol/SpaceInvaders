package View;

import java.awt.Color;
import java.util.ArrayList;

import Model.Enemies;
import Model.Enemy;
import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class Board extends GCompound {
	
	public final static int WIDTH = 600;
	public final static int HEIGHT = 600;
	public final static int ENEMY_SIZE = 30;
	private GRect background;
	
	public Board() {
	}
	
	//draws spaceship as a rectangle
	public void drawShip(Spaceship ship) {
		drawBackground();
		GRect rect = new GRect(ship.getX() - 10,ship.getY() - 10,20,20);
		rect.setFilled(true);
		rect.setColor(Color.GREEN);
		add(rect);
	}
	
	public void drawEnemies(Enemies enemies) {
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for(Enemy enemy :enemyList ) {
			drawEnemy(enemy);
		}
	}
	
	public void drawEnemy(Enemy enemy) {
		GRect rect = new GRect(enemy.getX() - ENEMY_SIZE / 2, enemy.getY() - (ENEMY_SIZE / 2), ENEMY_SIZE, ENEMY_SIZE);
		rect.setFilled(true);
		rect.setColor(Color.RED);
		add(rect);
	}
	
	private void drawBackground() {
		background = new GRect(WIDTH, HEIGHT);
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}

	public void drawShoots(Spaceship ship) {
		for(Shoot shoot : ship.shoots) {
			GOval oval = new GOval(shoot.getX() - 5,shoot.getY() - 5,10,10);
			oval.setFilled(true);
			oval.setColor(Color.WHITE);
			add(oval);
			System.out.println(ship.shoots.size());
		}
	}
}
