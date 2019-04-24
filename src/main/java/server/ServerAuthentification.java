package server;

import java.io.*;

import common.AuthentificationMessage;
import common.TextMessage;

//package private
class ServerAuthentification implements Runnable{
	static private final String PASSWORD = "hodor";
	Connection connection;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;

	public ServerAuthentification(Connection c) {
		this.connection = c;
		this.inputStream = connection.inputStream;
		this.outputStream = connection.outputStream;
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
		try {
			connection.send("Requesting password...");
			//AuthentificationMessage msg = (AuthentificationMessage) inputStream.readObject();
			TextMessage msg = (TextMessage) inputStream.readObject();
			if (login(msg.getContent())) {
				connection.send("Authentification successful!");
				connection.start();
			} else {
				connection.send("Authentification failed!");
				connection.close();
			}			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}