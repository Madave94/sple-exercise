package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import common.TextDecorator;

import common.AuthentificationMessage;
import common.LoginMessage;
import common.TextMessage;
import common.UserMessage;

/**
 * class for an individual connection to a client. allows to send messages to
 * this client and handles incoming messages.
 */
public class Connection extends Thread {
	protected Socket socket;
	public ObjectInputStream inputStream;
	public ObjectOutputStream outputStream;
	private Server server;
	private String clientName;

	public String getClientName() {
		return clientName;
	}

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
		clientName = socket.getInetAddress().toString();
		try {
			server.broadcast(clientName + " has joined.");
			Object msg = null;
			while ((msg = inputStream.readObject()) != null) {
				handleIncomingMessage(msg);
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
	private void handleIncomingMessage(Object msg) {
		if (msg instanceof TextMessage) {
			
			String incomingMessage = ((TextMessage) msg).getContent();
			server.broadcast(this.clientName + " - " + incomingMessage);
		}
		if (msg instanceof AuthentificationMessage) {
			boolean incomingMessage = ((AuthentificationMessage) msg).getContent();
			System.out.println("Authentification was " + incomingMessage);
		}
		if (msg instanceof LoginMessage) {
			this.clientName = ((LoginMessage) msg).getUsername();
		}
		if (msg instanceof UserMessage) {
			String[] userMsg = ((UserMessage) msg).getAll();
			server.sendToUser(userMsg[0], userMsg[1], userMsg[2]);
		}
	}

	/**
	 * sends a message to the client
	 * 
	 * @param line
	 *            text of the message
	 */
	public void send(String line) {
		send(new TextDecorator(line));
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
	
	public void send(AuthentificationMessage msg) {
		try {
			synchronized (outputStream) {
				outputStream.writeObject(msg);
			}
			outputStream.flush();
		} catch (IOException ex) {
		}
	}
	
	public void close() throws IOException {
		if (outputStream != null) outputStream.close();
	}
}
