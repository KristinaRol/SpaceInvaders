package Model;

import java.util.ArrayList;

import View.Board;

public class Enemies {
	
	private ArrayList<Enemy> enemmyList = new ArrayList<>();

	public Enemies(int rows, int columns) {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				
				enemmyList.add(new Enemy(1.0 * Board.WIDTH / columns * (col + 0.5) , 40 + row * 50));
			}
		}
	}

	public ArrayList<Enemy> getEnemmyList() {
		return enemmyList;
	}
	
}
