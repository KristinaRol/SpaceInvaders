package Model;

import java.util.ArrayList;

import View.Board;
import View.BoardFancy;

public class Spaceship {
	
	private double x;
	private double y;
	private int life;
	public ArrayList<Shoot> shoots = new ArrayList<Shoot>();


	public Spaceship(BoardFancy board) {
		this.x = 50;
		this.y = 75;
		this.life = 3;
	}
	
	public Spaceship(Board board) {
		this.x = 13;
		this.y = 12;
		this.life = 3;
	}


	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	public void shoot() {
		Shoot shoot = new Shoot(x, y);
		shoots.add(shoot);
	}
	
}
