package common;
/**
 * serializable message that can be send over the sockets between client and
 * server. 
 */
public class TextMessage extends MessageProtocol {

	public TextMessage(String content) {
		super(content);
	}

	@Override
	public String getContent() {
		return (String) content;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o != null && o instanceof TextMessage) {
			TextMessage msg = (TextMessage) o;
			String otherContent = msg.getContent();
			String ownContent = this.getContent();
			if (ownContent.equals(otherContent)) result = true;
		}
		return result;
	}
}
