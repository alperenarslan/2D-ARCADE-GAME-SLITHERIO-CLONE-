package items;

import java.awt.Color;

public class Poison extends Food implements Item{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Poison(int xPos, int yPos) {
		super(xPos, yPos, 25, Color.BLACK);
	}

}
