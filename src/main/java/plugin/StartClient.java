package plugin;

import client.Client;

public class StartClient extends PluginConfig {
	
	public static void main (String args[]) {
		plugin = new StartClient();
		new Client(args);
	}
}
