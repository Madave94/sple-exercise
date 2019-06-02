package plugin;

import client.*;

public class GUIPlugin implements ChatLineListenerPlugin{

	@Override
	public ChatLineListener getChatLineListener(Client client) {
		return new Swing_GUI(client);
	}

}
