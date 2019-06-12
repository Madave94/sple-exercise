package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextUserMessage {
	
	@Test
	public void TextUserMessage() {
		String msg = "/msg alice Hi, my name is bob!";
		String recieverResult = "alice";
		String msgResult = "Hi, my name is bob!";
		UserMessage usrMsg = new UserMessage ( msg );
		assertEquals(true, recieverResult.equals(usrMsg.getReciever()));
		assertEquals(true, msgResult.equals(usrMsg.getMessageContent()));
	}

}
