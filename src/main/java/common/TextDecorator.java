package common;

public class TextDecorator extends TextMessage{

	private static final long serialVersionUID = 1L;
	private String decorated_content;
	
	public TextDecorator(String content) {
		super(content);
		decorated_content = content;
		
		decorated_content = new TextColor().decorateText(decorated_content);
		
		decorated_content = SpamFilter.getInstance().filter(decorated_content);
		
		decorated_content = new Swap2Letters().encrypt(decorated_content);

		//decorated_content = new ROT13().encrypt(decorated_content);
	}
	
	@Override 
	public String getContent() {
		String sending_content = decorated_content;		
		
		sending_content = new Swap2Letters().decrypt(sending_content);
		
		//sending_content = new ROT13().decrypt(sending_content);
		
		return sending_content;
	}
	

}
