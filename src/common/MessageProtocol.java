package common;

import java.io.Serializable;

public abstract class MessageProtocol implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Object content;
	
	public MessageProtocol(Object content) {
		this.content = content;
	}
	
	public Object getContent() {
		return content;
	}

}
