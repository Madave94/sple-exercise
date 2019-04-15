package common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestTextDecorator {
	
	TextDecorator decorateMe;
	
	@Before
	public void initializeTests() {
		decorateMe = new TextDecorator();
	}
	
	@Test
	public void testApplyDecorator () {
		String message = "I want to be red.";
		String command = "RED";
		String[] combined = new String[] {message, command};
		String expected = "\\033[0;31mI want to be red.\\033[0m";
		assertEquals(expected, decorateMe.applyDecorator(combined));
	}

	@Test
	public void testFindNextCommand() {
		String message = "<blue>This should be blue.</blue>";
		String[] expected = new String[] {"BLUE", "This should be blue."};
		assertArrayEquals(expected, decorateMe.findNextCommand(message));
	}
	
	@Test
	public void testDecorateText() {
		String message = "<cyan>Please make me cyan.</cyan>";
		String expected = "\\033[0;36mPlease make me cyan.\\\\033[0m";
		assertEquals(expected, decorateMe.decorateText(message));
	}

}
