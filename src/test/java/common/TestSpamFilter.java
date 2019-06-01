package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSpamFilter {
	
	@Test
	public void spamTest1() {
		String message = "fuck this, man!";
		String expected = "**** this, man!";
		String result = new SpamFilter(message).filter();
		assertEquals(true, expected.equals(result));
	}
	
	@Test
	public void spamTest2() {
		String message = "fuck this shit, man!";
		String expected = "**** this ****, man!";
		String result = new SpamFilter(message).filter();
		assertEquals(true, expected.equals(result));
	}
	
	@Test
	public void spamTest3() {
		String message = "you are an idiot!";
		String expected = "you are an *****!";
		String result = new SpamFilter(message).filter();
		assertEquals(true, expected.equals(result));
	}

}
