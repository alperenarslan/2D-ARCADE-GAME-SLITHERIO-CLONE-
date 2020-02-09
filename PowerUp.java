package items;

import java.awt.Color;

public class PowerUp extends Food implements Item{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PowerUp(int xPos, int yPos) {
		super(xPos, yPos, 50, Color.GREEN);
	}
	
	

}
