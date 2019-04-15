package server;

//package private
class ServerAuthentification {
	static private final String PASSWORD = "hodor";
	
	boolean login(String check) {
		boolean result = false;
		if (check.equals(PASSWORD)) result = true;
		return result;
	}
	
}
