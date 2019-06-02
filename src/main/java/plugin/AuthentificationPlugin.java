package plugin;

import client.*;
import server.*;

public interface AuthentificationPlugin {
	
	ServerAuthentification getConnectionBlocker(Connection c);

	void sendPassword(Client c, String password);

}
