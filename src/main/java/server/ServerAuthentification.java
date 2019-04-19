package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

import common.TextMessage;

//package private
class ServerAuthentification implements Runnable{
	static private final String PASSWORD = "hodor";
	Server server;
	Socket client;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	
	public ServerAuthentification(Server server, Socket client) {
		this.server = server;
		this.client = client;
		Thread thread = new Thread(this);
		thread.start();
	}

	boolean login(String check) {
		boolean result = false;
		if (check.equals(PASSWORD)) result = true;
		return result;
	}

	@Override
	public void run() {
		System.out.println("[Client " + client.getInetAddress() + "] tries to connect.");
		
		//connect client streams
		try {
			this.outputStream = new ObjectOutputStream((client.getOutputStream()));
			this.inputStream = new ObjectInputStream((client.getInputStream()));
			outputStream.writeObject("Please enter the password!");
			TextMessage answer = (TextMessage) inputStream.readObject();
			if (login(answer.getContent())) {
				System.out.println("Accepted from " + client.getInetAddress());
				Connection c = server.connectTo(client);
				c.start();
			}
		} catch (IOException | ClassNotFoundException e) { e.printStackTrace();}
		finally {
			try {
				outputStream.close();
				inputStream.close();
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
}
