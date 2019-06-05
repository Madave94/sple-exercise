package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

import java.util.logging.*;

import common.AuthentificationMessage;
import plugin.AuthentificationPlugin;
import plugin.LoggingPlugin;

/**
 * server's main class. accepts incoming connections and allows broadcasting
 */
public class Server {
	
	// initialize Plugins
	static AuthentificationPlugin authentificationPlugin;
	static LoggingPlugin loggingPlugin;

	/**
	 * list of all known connections
	 */
	protected HashSet<Connection> connections = new HashSet<Connection>();

	public static void main(String args[]) throws IOException {
		launcher(args);
	}
	
	public static void launcher(String args[]) throws IOException {
		if (args.length == 0) 
		{
			System.out.println("Starting a default server with port 1025...");
			new Server(1025);
		} else if (args.length != 1) 
		{
			throw new RuntimeException("Syntax: ChatServer <port>");
		} 
		else new Server(Integer.parseInt(args[0]));
	}

	/**
	 * awaits incoming connections and creates Connection objects accordingly.
	 * 
	 * @param port
	 *            port to listen on
	 * @throws IOException 
	 */
	public Server(String args[], AuthentificationPlugin authentificationPlugin, LoggingPlugin loggingPlugin) throws IOException {
		Server.authentificationPlugin = authentificationPlugin;
		Server.loggingPlugin = loggingPlugin;
		launcher(args);
	}
	
	public Server(int port) throws IOException {
		
		// Setting logger
		if (loggingPlugin != null) loggingPlugin.reportInit();

		System.out.println("Initialized Logger");
		
		
		// Creating ServerSocket
		ServerSocket server = new ServerSocket(port);
		while (true) {
			System.out.println("Waiting for Connections...");

			if (loggingPlugin != null) loggingPlugin.reportConnectionWait();
			
			Socket client = server.accept();			
			
			System.out.println("Accepted from " + client.getInetAddress());
			
			if (loggingPlugin != null) loggingPlugin.reportConnectionAccepted(client);
			
			Connection c = connectTo(client);
			
			if (authentificationPlugin == null) c.start();
			else authentificationPlugin.getConnectionBlocker(c);
		}
	}
	
	/**
	 * creates a new connection for a socket
	 * 
	 * @param socket
	 *            socket
	 * @return the Connection object that handles all further communication with
	 *         this socket
	 */
	public Connection connectTo(Socket socket) {
		if (loggingPlugin != null) loggingPlugin.reportConnectionCreated(socket);
		
		Connection connection = new Connection(socket, this);
		connections.add(connection);
		return connection;
	}

	/**
	 * send a message to all known connections
	 * 
	 * @param text
	 *            content of the message
	 */
	public void broadcast(String text) {
		if (loggingPlugin != null) loggingPlugin.reportBroadcastMsg(text);
		
		synchronized (connections) {
			for (Iterator<Connection> iterator = connections.iterator(); iterator.hasNext();) {
				Connection connection = (Connection) iterator.next();
				connection.send(text);
			}
		}
	}
	
	/**
	 * remove a connection so that broadcasts are no longer sent there.
	 * 
	 * @param connection
	 *            connection to remove
	 */
	public void removeConnection(Connection connection) {
		if (loggingPlugin != null) loggingPlugin.reportRemoveConnection(connection);
		
		connections.remove(connection);
	}
	
	public void close() {
		AuthentificationMessage hasAccess = new AuthentificationMessage(false);
		
		if (loggingPlugin != null) loggingPlugin.reportRemoveAll();		
		
		synchronized (connections) {
			for (Iterator<Connection> iterator = connections.iterator(); iterator.hasNext();) {
				Connection connection = (Connection) iterator.next();
				connection.send(hasAccess);
				removeConnection(connection);
			}
		}
	}
}
