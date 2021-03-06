package common;

public class UserMessage extends MessageProtocol{
	
	private String reciever;
	private String consignor;
	private TextMessage msg;

	public UserMessage(String content, String consignor ) {
		super(content);
		String[] tokens = this.getContent().split(" ", 3);
		this.reciever = tokens[1].trim();
		this.msg = new TextMessage ( tokens[2].trim() );
		this.consignor = consignor;
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
	
	public String getConsignor() {
		return consignor;
	}
	
	public String[] getAll() {
		String[] contents = { this.reciever, this.consignor, this.msg.getContent() };
		return contents;
	}
}
