package client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import common.AuthentificationMessage;

import common.TextMessage;

import common.TextDecorator;

import plugin.*;


/**
 * simple chat client
 */
public class Client implements Runnable{
	
	// Initialize Plugins
	private static ChatLineListenerPlugin chatLineListenerPlugin;
	private static AuthentificationPlugin authentificationPlugin;
	
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	private String PASSWORD;
	
	protected Thread thread;
	
	public static void main(String args[]) throws IOException {
		launcher(args);
	}
	
	static public void launcher(String args[]) {
		Client client;
		if (args.length == 0) client = new Client("localhost", 1025);
		else if (args.length == 3) client = new Client(args[0], Integer.parseInt(args[1]), args[2]);
		else if (args.length != 2) throw new RuntimeException("Syntax: ChatClient <host> <port>");
		else client = new Client(args[0], Integer.parseInt(args[1]));
		
		if (chatLineListenerPlugin == null) new Console(client);
		else chatLineListenerPlugin.getChatLineListener(client);

	}
	
	public Client(String args[], AuthentificationPlugin authentificationPlugin, ChatLineListenerPlugin chatLineListener) {
		this.authentificationPlugin = authentificationPlugin;
		this.chatLineListenerPlugin = chatLineListenerPlugin;
		launcher(args);
	}
		
	public Client(String host, int port) {
		// Using default correct password
		this(host, port, "hodor");
	}
	
	public Client(String host, int port, String password) {
		
		setPassword(password);

		try {
			System.out.println("Connecting to " + host + " (port " + port + ")...");
			
			Socket s = new Socket(host, port);
			
			this.outputStream = new ObjectOutputStream((s.getOutputStream()));
			this.inputStream = new ObjectInputStream((s.getInputStream()));	
			
			thread = new Thread(this);
			
			if (authentificationPlugin != null) 
				authentificationPlugin.sendPassword(this, PASSWORD);
			
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * main method. waits for incoming messages.
	 */
	public void run() {
		try {
			Thread thisthread = Thread.currentThread();
			while (thread == thisthread && !thisthread.interrupted()) {
				try {
					Object msg = inputStream.readObject();
					handleIncomingMessage(msg);
				} catch (EOFException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			thread = null;
			try {
				outputStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * decides what to do with incoming messages
	 * 
	 * @param msg
	 *            the message (Object) received from the sockets
	 */
	protected void handleIncomingMessage(Object msg) {
		if (msg instanceof TextMessage) {
			fireAddLine(((TextMessage) msg).getContent() + "\n");
		}
		// kick the client if the Authentification is false
		if (msg instanceof AuthentificationMessage) {
			try {
				handleAuthentificationMessage(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleAuthentificationMessage(Object msg) throws IOException {
		boolean hasAccess = ((AuthentificationMessage) msg).getContent();
		if (!hasAccess) close();
	}

	public void send(String line) {
		send(new TextDecorator(line));

		//send(new TextMessage(line));
	}

	public void send(TextMessage msg) {
		try {
			outputStream.writeObject(msg);
			outputStream.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			this.stop();
		}
	}
	
	/**
	 * listener-list for the observer pattern
	 */
	private ArrayList<ChatLineListener> listeners = new ArrayList<ChatLineListener>();

	/**
	 * addListner method for the observer pattern
	 */
	public void addLineListener(ChatLineListener listener) {
		listeners.add(listener);
	}

	/**
	 * remove Listner method for the observer pattern
	 */
	public void removeLineListener(ChatLineListener listner) {
		listeners.remove(listner);
	}

	/**
	 * fire Listner method for the observer pattern
	 */
	public void fireAddLine(String line) {
		for (Iterator<ChatLineListener> iterator = listeners.iterator(); iterator.hasNext();) {
			ChatLineListener listener = (ChatLineListener) iterator.next();
			listener.newChatLine(line);
		}
	}

	public void stop() {
		thread = null;
	}
	
	public void close() throws IOException {
		if (thread.isAlive()) thread.interrupt();
		if (outputStream != null) outputStream.close();
		System.exit(0);
	}
	
	public void setPassword(String password) {
		this.PASSWORD = password;
	}
	
	public void printListener() {
		for (ChatLineListener listener: listeners) {
			System.out.println(listener.toString());
		}
	}
}
