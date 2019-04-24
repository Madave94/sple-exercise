package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

/**
 * server's main class. accepts incoming connections and allows broadcasting
 */
public class Server {

	/**
	 * list of all known connections
	 */
	protected HashSet<Connection> connections = new HashSet<Connection>();

	public static void main(String args[]) throws IOException {
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
	 */
	public Server(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		System.out.println("Initialized Logger");
		while (true) {
			System.out.println("Waiting for Connections...");
			Socket client = server.accept();			
			
			System.out.println("Accepted from " + client.getInetAddress());
			Connection c = connectTo(client);
			
			//Authentification process
			new ServerAuthentification(c);
			
			//c.start();
			
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
		connections.remove(connection);
	}

}