package plugin;

public interface EncryptionPlugin {
	
	String encrypt(String msg);
	
	String decrypt(String msg);

}
