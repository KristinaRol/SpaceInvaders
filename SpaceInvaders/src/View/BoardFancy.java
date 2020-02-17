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
import acm.graphics.GPoint;
import acm.graphics.GRect;

public class BoardFancy extends GCompound {
	
	public final static int WIDTH =  616;
	public final static int HEIGHT = 600;
	public final static int ENEMY_SIZE = 30;
	private GRect background;
	GImage img = new GImage("Spaceship.png");
	GImage heart = new GImage("heart.png");
	GImage alien = new GImage("alien.png");
	
	public int getIntWidth() {
		return WIDTH;
	}
	public int getIntHeight() {
		return HEIGHT;
	}
	public int getBaseWidth() {
		return  WIDTH;
	}
	
	//draws spaceship as a rectangle
	public void drawShip(Spaceship ship) {
		drawBackground();
		
		img.setLocation(ship.getX() * 5.9,ship.getY() *5.9);
		img.setVisible(true);
		add(img);
	}
	
	public void drawEnemies(Enemies enemies) {
		ArrayList<Enemy> enemyList = enemies.getEnemmyList();
		for(Enemy enemy :enemyList ) {
			drawEnemy(enemy);
		}
	}
	
	public void drawEnemy(Enemy enemy) {
		
		GImage alien = new GImage(this.alien.getImage());
		alien.setLocation(enemy.getX()*21, enemy.getY()*21 );
		add(alien);
	}
	
	private void drawBackground() {
		background = new GRect(WIDTH,HEIGHT); 
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}

	public void drawShoots(Spaceship ship) {
		for(Shoot shoot : ship.shoots) {
			GOval oval = new GOval(shoot.getX() * 5.9 + 8,shoot.getY() * 5.9 ,10,10);
			oval.setFilled(true);
			oval.setColor(Color.WHITE);
			add(oval);
		}
	}

	public void drawLife(Spaceship ship) {
		for(int i = 0; i<ship.getLife(); i++) {
		GImage heart = new GImage(this.heart.getImage());
		heart.setLocation(500+i*25, 520);
		add(heart);
		}
		
	}
	public double getMaxXFromShip() {
		return 97;
	}
	public double getVelocityMultiplier() {
		return 2;
	}
}

