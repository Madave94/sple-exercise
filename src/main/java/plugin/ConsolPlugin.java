package plugin;

import client.*;

public class ConsolPlugin implements ChatLineListenerPlugin{

	@Override
	public ChatLineListener getChatLineListener(Client client) {
		return new Console(client);
	}
	

}
