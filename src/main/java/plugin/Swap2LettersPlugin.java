package plugin;

import common.Swap2Letters;

public class Swap2LettersPlugin implements EncryptionPlugin {

	public String encrypt(String msg) {
		return new Swap2Letters().encrypt(msg);
	}

	public String decrypt(String msg) {
		return new Swap2Letters().decrypt(msg);
	}

}
