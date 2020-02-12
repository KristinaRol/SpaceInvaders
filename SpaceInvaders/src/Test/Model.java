package Test;

import java.util.ArrayList;

import Model.Spaceship;
import acm.graphics.GRect;


public class Model {
	
	public static final double WIDTH = 480;
	public static final double HEIGHT = 320;

	public Spaceship ship = new Spaceship(20.0,20.0);
	//private ArrayList<Enemy> enemies;
	
	public void addShip(View view) {
		view.add(ship.rect);
	}
	
	
}
