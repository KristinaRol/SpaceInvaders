package Test;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GRect;

public class View extends GCompound {


	/* Private instance variables */
	private GRect background;
	
	/** Creates a new HousePointsView with a given model and size */
	public View(double width, double height) {
		background = new GRect(width, height);
		background.setFilled(true);
		background.setColor(Color.WHITE);
	}
	
	
	/** Updates the display image from the model */
	public void update(Model model) {
		removeAll();
		add(background);
	}
}
