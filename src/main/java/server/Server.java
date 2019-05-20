package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

//#if ServerLogging
import java.util.logging.*;
//#endif

//#if Authentification
import common.AuthentificationMessage;
//#endif

/**
 * server's main class. accepts incoming connections and allows broadcasting
 */
public class Server {

	/**
	 * list of all known connections
	 */
	protected HashSet<Connection> connections = new HashSet<Connection>();
	
	//#if ServerLogging
	private static final Logger log = Logger.getLogger(Server.class.getName());
	//#endif

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
		// Setting logger
		//#if ServerLogging
		log.setUseParentHandlers(false);
		Handler handler = new FileHandler( "log.xml" );
		log.addHandler(handler);
		log.info("Initialized Logger");
		//#endif

		System.out.println("Initialized Logger");
		
		
		// Creating ServerSocket
		ServerSocket server = new ServerSocket(port);
		while (true) {
			System.out.println("Waiting for Connections...");
			
			//#if ServerLogging
			log.info("Waiting for Connections...");
			//#endif
			
			Socket client = server.accept();			
			
			System.out.println("Accepted from " + client.getInetAddress());
			
			//#if ServerLogging
			log.info("Accepted from " + client.getInetAddress());
			//#endif
			
			Connection c = connectTo(client);
			
			//#if Authentification
			new ServerAuthentification(c);
			//#else
//@			c.start();
			//#endif
			
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
		//#if ServerLogging
		log.info("Connected the client: " + socket.getInetAddress());
		//#endif
		
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
		//#if ServerLogging
		log.info("Broadcast message: " + text);
		//#endif
		
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
		//#if ServerLogging
		log.info("Removed connection: " + connection.socket.getInetAddress());
		//#endif
		
		connections.remove(connection);
	}
	
	//#if Authentification
	public void close() {
		AuthentificationMessage hasAccess = new AuthentificationMessage(false);
		
		//#if ServerLogging
		log.warning("Kicked all clients.");
		//#endif
		
		synchronized (connections) {
			for (Iterator<Connection> iterator = connections.iterator(); iterator.hasNext();) {
				Connection connection = (Connection) iterator.next();
				connection.send(hasAccess);
				removeConnection(connection);
			}
		}
	}
	//#endif

}
