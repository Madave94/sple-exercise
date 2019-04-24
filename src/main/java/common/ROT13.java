package common;

public class ROT13 extends Encryption{

	// https://stackoverflow.com/questions/8981296/rot-13-function-in-java
	
	@Override
	public String encrypt(String msg) {
		String newMsg = "";
		for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            newMsg += c;
		}
		return newMsg;
	}

	// https://stackoverflow.com/questions/25537465/rot13-decode-in-java
	
	@Override
	public String decrypt(String msg) {
		String newMsg = encrypt(msg);
		return newMsg;
	}

}
