package MVCTest;

import javax.swing.JPanel;

import acm.program.GraphicsProgram;

public class HousePoints extends GraphicsProgram {
	
	// Constants that define the size of the views
	private double GRAPHWIDTH = 400;
	private double GRAPHHEIGHT = 400;
	
	public static void main(String[] args) {
		new HousePoints().start();
	}

	public void init() {
		// Create a model
		HousePointsModel model = new HousePointsModel();
		
		// Create bar graph view
		BarGraphView barview = new BarGraphView(GRAPHWIDTH, GRAPHHEIGHT);
		model.addView(barview);
		add(barview);
		
		// Create pie chart view
		PieChartView pieview = new PieChartView(GRAPHWIDTH, GRAPHHEIGHT);
		model.addView(pieview);
		add(pieview, GRAPHWIDTH, 0);
		
		// The panel where the controller places its interactors
		JPanel controllerPanel = getRegionPanel(SOUTH);
		
		// Create controller
		HousePointsController controller = new HousePointsController(model, controllerPanel);
	}
}
