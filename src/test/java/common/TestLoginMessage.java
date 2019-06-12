package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestLoginMessage {

	@Test
	public void testUserNameMsg() {
		String msg = "/username bob";
		String expectedResult = "bob";
		LoginMessage l_msg = new LoginMessage(msg);
		assertEquals(true, expectedResult.equals(l_msg.getUsername()));
	}

}
