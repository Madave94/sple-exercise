package common;

public class TextDecorator extends TextMessage{

	private static final long serialVersionUID = 1L;
	public String decorated_content;
	
	public TextDecorator(String content) {
		super(content);
		decorated_content = content;
	}
	
	@Override 
	public String getContent() {
		String sending_content = decorated_content;		
		
		return sending_content;
	}
	

}
