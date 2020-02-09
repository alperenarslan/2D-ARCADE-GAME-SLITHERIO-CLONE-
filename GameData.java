package game;

import java.util.ArrayList;

import items.Player;

public class GameData {
	
	private static GameData instance = new GameData();
	
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public static GameData getInstance() {
		return instance;
	}

	public static void setInstance(GameData instance) {
		GameData.instance = instance;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	private GameData() {
		
	}

}
