package plugin;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import client.*;
import common.*;
import server.*;

public class ConifSubstitutePlugin implements SubstitutePlugin {

	// feature Authentification
	public ServerAuthentification getConnectionBlocker(Connection c) {
		return new ServerAuthentification(c);
	}

	public void sendPassword(Client c, String password) {
		c.send(password);
	}

	// feature UI
	public ChatLineListener getChatLineListener(Client client) {
		return new Swing_GUI(client);
	}

	// feature encryption
	public String encrypt(String msg) {
		return new ROT13().encrypt(msg);
	}

	public String decrypt(String msg) {
		return new ROT13().decrypt(msg);
	}

	// feature SpamFilter
	public String filter(String msg) {
		return SpamFilter.getInstance().filter(msg);
	}
	
	// feature Logging
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

	// feature Color
	public String addColor(String msg) {
		return new TextColor().decorateText(msg);
	}

}
