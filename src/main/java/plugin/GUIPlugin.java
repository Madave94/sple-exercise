package plugin;

import client.*;

public class GUIPlugin implements ChatLineListenerPlugin{

	public ChatLineListener getChatLineListener(Client client) {
		return new Swing_GUI(client);
	}

}
