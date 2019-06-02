package plugin;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import server.Connection;
import server.Server;

public class FileLoggingPlugin implements LoggingPlugin {

	private static final Logger log = Logger.getLogger(Server.class.getName());
	
	public boolean isLoggingActive() {
		return true;
	}

	public void reportInit() {
		log.setUseParentHandlers(false);
		Handler handler;
		try {
			handler = new FileHandler( "log.xml" );
			log.addHandler(handler);
			log.info("Initialized Logger");	
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reportConnectionWait() {
		log.info("Waiting for Connections...");
	}

	public void reportConnectionAccepted(Socket client) {
		log.info("Accepted from " + client.getInetAddress());
	}

	public void reportConnectionCreated(Socket client) {
		log.info("Connected the client: " + client.getInetAddress());
	}

	public void reportBroadcastMsg(String msg) {
		log.info("Broadcast message: " + msg);
	}

	public void reportRemoveConnection(Connection c) {
		log.info("Removed connection: " + c.socket.getInetAddress());
	}

	public void reportRemoveAll() {
		log.warning("Kicked all clients.");		
	}

}
