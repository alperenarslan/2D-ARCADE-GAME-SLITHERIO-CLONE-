package items;

import java.awt.Color;

public class PowerDown extends Food implements Item{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PowerDown(int xPos, int yPos) {
		super(xPos, yPos, 50, Color.RED);
	}

}
