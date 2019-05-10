package common;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestTextMessage {
	
	@Test
	public void testEqualsTrue() {
		TextMessage msg1 = new TextMessage("Das ist ein Test.");
		TextMessage msg2 = new TextMessage("Das ist ein Test.");
		assertEquals(true, msg1.equals(msg2));		
	}
	
	@Test
	public void testEqualsFalse() {
		TextMessage msg1 = new TextMessage("Das ist ein Test.");
		TextMessage msg2 = new TextMessage("Das ist nicht gleich.");
		assertEquals(false, msg1.equals(msg2));		
	}
	
}
