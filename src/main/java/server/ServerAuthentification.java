package server;

import java.net.*;

//package private
class ServerAuthentification implements Runnable{
	static private final String PASSWORD = "hodor";
	
	public ServerAuthentification(ServerSocket server, Socket client) {
		// TODO Auto-generated constructor stub
	}

	boolean login(String check) {
		boolean result = false;
		if (check.equals(PASSWORD)) result = true;
		return result;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
