package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import common.TextDecorator;

//#if Authentification
//@import common.AuthentificationMessage;
//#endif

import common.TextMessage;

/**
 * class for an individual connection to a client. allows to send messages to
 * this client and handles incoming messages.
 */
public class Connection extends Thread {
	protected Socket socket;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	private Server server;

	public Connection(Socket s, Server server) {
		this.socket = s;
		try {
			inputStream = new ObjectInputStream((s.getInputStream()));
			outputStream = new ObjectOutputStream((s.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.server = server;			
	}

	/**
	 * waits for incoming messages from the socket
	 */
	public void run() {
		String clientName = socket.getInetAddress().toString();
		try {
			server.broadcast(clientName + " has joined.");
			Object msg = null;
			while ((msg = inputStream.readObject()) != null) {
				handleIncomingMessage(clientName, msg);
			}
		} catch (SocketException e) {
		} catch (EOFException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			server.removeConnection(this);
			server.broadcast(clientName + " has left.");
			try {
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * decides what to do with incoming messages
	 * 
	 * @param name
	 *            name of the client
	 * @param msg
	 *            received message
	 */
	private void handleIncomingMessage(String name, Object msg) {
		if (msg instanceof TextMessage) {
			
			String incomingMessage = ((TextMessage) msg).getContent();
			server.broadcast(name + " - " + incomingMessage);
		}
		//#if Authentification
//@		if (msg instanceof AuthentificationMessage) {
//@			boolean incomingMessage = ((AuthentificationMessage) msg).getContent();
//@			System.out.println("Authentification was " + incomingMessage);
//@		}
		//#endif
	}

	/**
	 * sends a message to the client
	 * 
	 * @param line
	 *            text of the message
	 */
	public void send(String line) {
		//#if TextDecorator || Rot13 || Swap2Letters
		send(new TextDecorator(line));
		//#else
//@		send(new TextMessage(line));
		//#endif
	}

	public void send(TextMessage msg) {
		try {
			synchronized (outputStream) {
				outputStream.writeObject(msg);
			}
			outputStream.flush();
		} catch (IOException ex) {
		}
	}
	
	//#if Authentification
//@	public void send(AuthentificationMessage msg) {
//@		try {
//@			synchronized (outputStream) {
//@				outputStream.writeObject(msg);
//@			}
//@			outputStream.flush();
//@		} catch (IOException ex) {
//@		}
//@	}
	//#endif
	
	public void close() throws IOException {
		if (outputStream != null) outputStream.close();
	}
}
