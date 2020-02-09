package items;

import java.awt.Color;
import java.util.Random;

public class Player {
	
	private String username;
	private double score;
	private Snake s;
	private Food[] foods = new Food[30];
	private Poison[] poisons = new Poison[5];
	private PowerUp powerUps;
	private PowerDown powerDowns;
	
	public Snake getS() {
		return s;
	}

	public void setS(Snake s) {
		this.s = s;
	}

	public Food[] getFoods() {
		return foods;
	}

	public void setFoods(Food[] foods) {
		this.foods = foods;
	}

	public Poison[] getPoisons() {
		return poisons;
	}

	public void setPoisons(Poison[] poisons) {
		this.poisons = poisons;
	}

	public PowerUp getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(PowerUp powerUps) {
		this.powerUps = powerUps;
	}

	public PowerDown getPowerDowns() {
		return powerDowns;
	}

	public void setPowerDowns(PowerDown powerDowns) {
		this.powerDowns = powerDowns;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Player(String username, double score) {
		this.username = username;
		this.score = score;
		
		s = new Snake();
		Random rand = new Random();
		int color = rand.nextInt(5);
		s.setColor(color);
		
		for (int i=0;i<30;i++) {
			int x = rand.nextInt(34);
			int y = rand.nextInt(17);
			// Java 'Color' class takes 3 floats, from 0 to 1.
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Food f = new Food(0,0,25,new Color(r, g, b));
			f = new Food(f.getxPositions()[x],f.getyPositions()[y],25,new Color(r, g, b));
			foods[i]=f;
		}
		for (int i=0;i<5;i++) {
			int x = rand.nextInt(34);
			int y = rand.nextInt(17);
			Poison f = new Poison(0,0);
			f = new Poison(f.getxPositions()[x],f.getyPositions()[y]);
			poisons[i]=f;
		}
	}

}
