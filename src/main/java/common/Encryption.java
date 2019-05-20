//#if Rot13 || Swap2Letters
package common;

abstract class Encryption {
	
	public TextMessage encrypt (TextMessage msg) {
		String content = msg.getContent();
		String encryptedContent = encrypt(content);		
		return new TextMessage(encryptedContent);
	}
	
	public TextMessage decrypt (TextMessage msg) {
		String content = msg.getContent();
		String decryptedContent = decrypt(content);
		return new TextMessage(decryptedContent);
	}
	
	public String encrypt(String msg) {
		return null;
	}
	
	public String decrypt(String msg) {
		return null;
	}

}
//#endif