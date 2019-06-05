package plugin;

import java.net.Socket;

import client.*;
import server.*;

public interface SubstitutePlugin {
	
	// Feature Authentification
	ServerAuthentification getConnectionBlocker(Connection c);

	void sendPassword(Client c, String password);
	
	// Feature UI
	ChatLineListener getChatLineListener(Client client);
	
	// Feature Encryption
	String encrypt(String msg);
	
	String decrypt(String msg);
	
	// Feature SpamFilter
	String filter(String msg);
	
	// Feature Logging
	boolean isLoggingActive();
	
	void reportInit();
	
	void reportConnectionWait();
	
	void reportConnectionAccepted(Socket client);
	
	void reportConnectionCreated(Socket client);
	
	void reportBroadcastMsg(String msg);
	
	void reportRemoveConnection(Connection c);
	
	void reportRemoveAll();
	
	// Feature Color
	String addColor(String msg);

}
