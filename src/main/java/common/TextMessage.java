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
		this.content = decoratedContent;
	}

	public String getContent() {
		
		return content;
	}
}
