package plugin;

import java.net.Socket;

import server.Connection;

public interface LoggingPlugin {
	
	boolean isLoggingActive();
	
	void reportInit();
	
	void reportConnectionWait();
	
	void reportConnectionAccepted(Socket client);
	
	void reportConnectionCreated(Socket client);
	
	void reportBroadcastMsg(String msg);
	
	void reportRemoveConnection(Connection c);
	
	void reportRemoveAll();

}
