package common;

import java.io.Serializable;

/**
 * serializable message that can be send over the sockets between client and
 * server. 
 */
public class TextMessage implements Serializable {

	private static final long serialVersionUID = -9161595018411902079L;
	private String content;
	

	public TextMessage(String content) {
		super();
		String decoratedContent = new TextDecorator().decorateText(content);
		String encryptedContent = new Swap2Letters().encrypt(decoratedContent);
		this.content = encryptedContent;
	}

	public String getContent() {
				
		return new Swap2Letters().decrypt(content);
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o != null && o instanceof TextMessage) {
			TextMessage msg = (TextMessage) o;
			String otherContent = msg.getContent();
			if (content.equals(otherContent)) result = true;
		}
		return result;
	}
}
