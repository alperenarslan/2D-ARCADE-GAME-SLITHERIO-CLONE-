package items;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Food extends JPanel implements Item{
	
	private int[] xPositions = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] yPositions = {150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550};
	
	public int[] getxPositions() {
		return xPositions;
	}

	public void setxPositions(int[] xPositions) {
		this.xPositions = xPositions;
	}

	public int[] getyPositions() {
		return yPositions;
	}

	public void setyPositions(int[] yPositions) {
		this.yPositions = yPositions;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xPos;
	
	private int size;
	
	private Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	
	public int getsize() {
		return size;
	}

	public void setsize(int size) {
		this.size = size;
	}

	private int yPos;
	
	public Food(int xPos, int yPos, int size, Color color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
		this.color = color;
	}
	
	public void paint(Graphics gr) {
		gr.setColor( color);
		gr.fillOval(xPos, yPos, size, size);
		
		gr.dispose();
	}

}
