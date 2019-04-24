package client;

import java.io.*;

public class ClientAuthentification implements Runnable{
	
	Client chatClient;
	Thread chatThread;
	protected ObjectInputStream inputStream;
	protected ObjectOutputStream outputStream;
	BufferedReader stdIn;

	public ClientAuthentification(Client chatClient) {
		this.chatClient = chatClient;
		this.chatThread = chatClient.thread;
		this.outputStream = chatClient.outputStream;
		this.inputStream = chatClient.inputStream;
		this.stdIn = chatClient.stdIn;
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		chatThread.start();		
	}

}
