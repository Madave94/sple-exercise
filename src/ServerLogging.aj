import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import server.Server;

public aspect ServerLogging {
	
	private static final Logger log = Logger.getLogger(Server.class.getName());
	
	pointcut start() : initialization(Server.new(*));
	
	pointcut logMe(String content) : 
		execution(void *.println(String))
		&& args(content)
		&& within(Server);
	
	before() : start() {
		log.setUseParentHandlers(false);
		Handler handler = null;
		try {
			handler = new FileHandler( "log.xml" );
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		log.addHandler(handler);
		log.info("Initialized Logger");
	}
	
	void around(String content) : logMe(content) {
		log.info(content);
		proceed(content);
	}
	
	
}