package Model;

import java.util.ArrayList;

import View.Board;

public class Enemies {
	
	private ArrayList<Enemy> enemmyList = new ArrayList<>();

	public Enemies(int rows, int columns) {
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				
				enemmyList.add(new Enemy(1 + col * 2, 1 + row * 2));
			}
		}
	}

	public ArrayList<Enemy> getEnemmyList() {
		return enemmyList;
	}
	
	public void removeDestroiedEnemies() {
		@SuppressWarnings("unchecked")
		ArrayList<Enemy> enemmyCheckList = (ArrayList<Enemy>) enemmyList.clone();
		for(Enemy enemy : enemmyCheckList) {
			if (enemy.getHitpoints() == 0) {
				enemmyList.remove(enemy);
			}
		}
	}
	
}
