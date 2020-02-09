package items;

import java.awt.Graphics;

public class Snake implements Item{

	
	private int[] snakeXlength = new int[1000000];
	private int lengthOfSnake = 3;
	private int color;
	public int getColor() {
		return color;
	}



	public void setColor(int color) {
		this.color = color;
	}



	public int getLengthOfSnake() {
		return lengthOfSnake;
	}



	public void setLengthOfSnake(int lengthOfSnake) {
		this.lengthOfSnake = lengthOfSnake;
	}



	public int[] getSnakeXlength() {
		return snakeXlength;
	}



	public void setSnakeXlength(int[] snakeXlength) {
		this.snakeXlength = snakeXlength;
	}



	public int[] getSnakeYlength() {
		return snakeYlength;
	}



	public void setSnakeYlength(int[] snakeYlength) {
		this.snakeYlength = snakeYlength;
	}



	public boolean isLeft() {
		return left;
	}



	public void setLeft(boolean left) {
		this.left = left;
	}



	public boolean isRight() {
		return right;
	}



	public void setRight(boolean right) {
		this.right = right;
	}



	public boolean isUp() {
		return up;
	}



	public void setUp(boolean up) {
		this.up = up;
	}



	public boolean isDown() {
		return down;
	}



	public void setDown(boolean down) {
		this.down = down;
	}



	private int[] snakeYlength = new int[1000000];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	
	
	public Snake() {
		
	}



	@Override
	public void paint(Graphics g) {
		
	}

}
