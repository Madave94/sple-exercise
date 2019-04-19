package server;

import java.io.IOException;
import java.io.*;
import java.net.*;

import common.TextMessage;

//package private
class ServerAuthentification implements Runnable{
	static private final String PASSWORD = "hodor";
	Server server;
	Socket client;
	protected BufferedReader inputStream;
	protected BufferedWriter outputStream;
	
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
			this.outputStream = new BufferedWriter (new OutputStreamWriter((client.getOutputStream())));
			this.inputStream = new BufferedReader (new InputStreamReader((client.getInputStream())));
			outputStream.write("Please enter the password!");
			outputStream.flush();
			String answer = inputStream.readLine();
			if (login(answer)) {
				System.out.println("Accepted from " + client.getInetAddress());
				Connection c = server.connectTo(client);
				c.start();
			}
		} catch (IOException e) { e.printStackTrace();}
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
