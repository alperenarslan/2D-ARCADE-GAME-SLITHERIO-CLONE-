package tcp;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

import game.GameData;
import items.Player;
import play.GameFrame;
import play.GamePanel;
import play.LoginFrame;

public class ServerThread extends Thread {
	
    private String line = new String();
    private BufferedReader is;
    private PrintWriter os;
    private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream(), true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			line = is.readLine();
			System.out.println(line);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("playerinfo.txt"), "utf-8"));
		    writer.write(line);
		} catch (IOException ex) {
		    // Report
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
		
		String[] parts = line.split(" ");
		String username = parts[0]; // 004
		String score = parts[1]; // 034556
		String width = parts[2];
		String height = parts[3];
		
		Player player = new Player(username, Double.parseDouble(score));
		GameData.getInstance().getPlayers().add(player);
		GamePanel.playerIndex = GameData.getInstance().getPlayers().indexOf(player);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame(GameData.getInstance().getPlayers().indexOf(player));
					Rectangle r = new Rectangle();
					r.width = Integer.parseInt(width);
					r.height = Integer.parseInt(height);
					frame.setBounds(r);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
	
}
