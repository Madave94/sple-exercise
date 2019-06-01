package common;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEncryption {
	
	@Test
	public void testROT13encryption() {
		String encryptMe = "Hallo wie gehts!";
		String result = new ROT13().encrypt(encryptMe);
		String expected = "Unyyb jvr trugf!";
		assertEquals(true, expected.equals(result));
	}
	
	@Test
	public void testROT13decryption() {
		String decryptMe = "Zve trugf thg!";
		String result = new ROT13().decrypt(decryptMe);
		String expected = "Mir gehts gut!";
		assertEquals(true, expected.equals(result));
	}
	
	@Test
	public void testSwapEncryption() {
		String encryptMe = "Hallo wie gehts!";
		String result = new Swap2Letters().encrypt(encryptMe);
		String expected = "aHllo wie gehts!";
		assertEquals(true, expected.equals(result));		
	}
	
	@Test
	public void testSwapDecryption() {
		String decryptMe = "iMr gehts gut!";
		String result = new Swap2Letters().decrypt(decryptMe);
		String expected = "Mir gehts gut!";
		assertEquals(true, expected.equals(result));		
	}
	
	/*
	 * This test don't work with the encryption included.
	 */
	@Test
	public void testEncryptedTextMessage() {
		TextMessage msg = new TextMessage("Codiere mich.");
		TextMessage result = new ROT13().encrypt(msg);
		TextMessage expected = new TextMessage("Pbqvrer zvpu.");
		assertEquals(true, expected.equals(result));
	}
	
	@Test
	public void testDecryptionTextMessage() {
		TextMessage msg = new TextMessage("Rapbqr zvpu.");
		TextMessage result = new ROT13().decrypt(msg);
		TextMessage expected = new TextMessage("Encode mich.");
		assertEquals(true, expected.equals(result));		
	}
	

}
