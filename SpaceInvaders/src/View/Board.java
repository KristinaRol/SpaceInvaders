package View;

import java.awt.Color;

import Model.Shoot;
import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class Board extends GCompound {
	
	public final static int WIDTH = 600;
	public final static int HEIGHT = 600;
	private GRect background;
	
	public Board() {
	}
	
	//draws spaceship as a rectangle
	public void drawShip(Spaceship ship) {
		drawBackground();
		GRect rect = new GRect(ship.getX(),ship.getY(),20,20);
		rect.setFilled(true);
		rect.setColor(Color.GREEN);
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
			GOval oval = new GOval(shoot.getX(),shoot.getY(),10,10);
			oval.setFilled(true);
			oval.setColor(Color.WHITE);
			add(oval);
			System.out.println(ship.shoots.size());
		}
	}
}
