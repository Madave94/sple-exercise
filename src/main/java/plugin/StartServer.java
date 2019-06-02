package plugin;

import java.io.IOException;

import server.Server;

public class StartServer extends PluginConfig{
	
	public static void main (String args[]) throws IOException {
		plugin = new StartServer();
		new Server(args, authentificationPlugin, loggingPlugin);
	}

}
