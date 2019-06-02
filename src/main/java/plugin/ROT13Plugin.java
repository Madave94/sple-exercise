package plugin;

import common.ROT13;

public class ROT13Plugin implements EncryptionPlugin {

	public String encrypt(String msg) {
		return new ROT13().encrypt(msg);
	}

	public String decrypt(String msg) {
		return new ROT13().decrypt(msg);
	}

}
