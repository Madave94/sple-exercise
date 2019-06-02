package plugin;

import client.*;

public class ConsolPlugin implements ChatLineListenerPlugin{

	public ChatLineListener getChatLineListener(Client client) {
		return new Console(client);
	}
	

}
