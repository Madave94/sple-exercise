package plugin;

import client.Client;
import server.*;

public class HandshakeAuthentificationPlugin implements AuthentificationPlugin{

	public ServerAuthentification getConnectionBlocker(Connection c) {
		return new ServerAuthentification(c);
	}
	
	public void sendPassword(Client c, String password) {
		c.send(password);
	}

}
