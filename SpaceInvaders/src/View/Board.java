package View;

import java.awt.Color;

import Model.Spaceship;
import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Board extends GCompound {
	
	public final static double WIDTH = 400;
	public final static double HEIGHT = 400;
	private GRect background;
	
	public Board() {
		background = new GRect(WIDTH, HEIGHT);
		background.setFilled(true);
		background.setColor(Color.WHITE);
	}
	
	//draws spaceship as a rectangle
	public void drawShip(Spaceship ship) {
		GRect rect = new GRect(ship.getX(),ship.getY(),20,20);
		this.add(rect);
	}
}
