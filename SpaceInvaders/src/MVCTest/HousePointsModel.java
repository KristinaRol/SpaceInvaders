package MVCTest;

import java.util.ArrayList;

public class HousePointsModel {
	
	/** Private instance variables */
	private int[] housePoints;
	private ArrayList<HousePointsView> views;

	/** Creates a new HousePointsModel with no views */
	public HousePointsModel() {
		housePoints = new int[0];
		views = new ArrayList<HousePointsView>();
	}

	/** Adds a view to the list of views for this model */
	public void addView(HousePointsView view) {
		views.add(view);
	}

	/** Sets the house points data to the contents of the integer array */
	public void setHousePoints(int[] points) {
		housePoints = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			housePoints[i] = points[i];
		}
		notifyViews();
	}

	/** Returns a copy of the internal house points data */
	public int[] getHousePoints() {
		int[] points = new int[housePoints.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = housePoints[i];
		}
		return points;
	}

	/** Calls update(this) on every view to reconstruct their displays */
	private void notifyViews() {
		for (HousePointsView view : views) {
			view.update(this);
		}
	}
}