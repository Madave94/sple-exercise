//#if Swap2Letters
package common;

class Swap2Letters extends Encryption{

	@Override
	public String encrypt(String msg) {
		if (msg.length() < 2) return msg;
		char[] c = msg.toCharArray();
		char temp = c[0];
		c[0] = c[1];
		c[1] = temp;
		return new String(c);
	}

	@Override
	public String decrypt(String msg) {
		String newMsg = encrypt(msg);
		return newMsg;
	}

}
//#endif
