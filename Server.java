package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static final int PORT = 4444;
	
	public static void main(String[] args) {
		try {
			new Server().runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runServer() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is up and ready for connections...");
		while(true) {
			Socket socket = serverSocket.accept();
			new ServerThread(socket).start();
		}
	}

}
