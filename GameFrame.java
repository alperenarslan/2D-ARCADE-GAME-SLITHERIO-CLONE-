package play;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	private int playerIndex;
	
	public GameFrame(int playerIndex) {
		this.playerIndex = playerIndex;
		bounds = getBounds();
		GamePanel gamePanel = new GamePanel(bounds, playerIndex);
		setContentPane(gamePanel);
		setBackground(new Color(79, 138, 197));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	public static Rectangle bounds;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*

	public static void main(String[] args) {
		
	}*/

}
