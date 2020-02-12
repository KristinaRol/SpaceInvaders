package Test;

import acm.graphics.GRect;

public class SpaceView extends View {

	public SpaceView(double width, double height) {
		super(width, height);
	}

	@Override
	public void createGraph() {
		GRect ship = new GRect(200,200);
	}

}
