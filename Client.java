package tcp;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
    public final static String ADDRESS = "192.168.1.33";
    public final static int PORT = 4444;
    
    private static BufferedReader is;
    private static PrintWriter os;
    private static Socket clientSocket;
    
    public Rectangle bounds;
    
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




	private String username;
    private double score;




    public Client(String username, double score, Rectangle bounds) throws UnknownHostException, IOException
    {
    	this.username = username;
    	this.score = score;
    	this.bounds = bounds;
    	clientSocket = new Socket(ADDRESS,PORT);
    	os = new PrintWriter(clientSocket.getOutputStream(),true);
    	is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    	
    	os.println(username+" "+score+" "+bounds.width+" "+bounds.height);
    	os.flush();
    }
    

    
}

