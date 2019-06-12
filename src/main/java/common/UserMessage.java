package common;

public class UserMessage extends MessageProtocol{
	
	private String reciever;
	private String consignor;
	private TextMessage msg;

	public UserMessage(Object content) {
		super(content);
		String[] tokens = this.getContent().split(" ", 3);
		this.reciever = tokens[1];
		this.msg = new TextMessage ( tokens[2] );
	}
	
	@Override
	public String getContent() {
		return (String) content;
	}
	
	public String getReciever() {
		return reciever;
	}

	public String getMessageContent() {
		return msg.getContent();
	}	

}
