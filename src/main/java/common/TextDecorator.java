//#if TextColor || Rot13 || Swap2Letters
package common;

public class TextDecorator extends TextMessage{

	private static final long serialVersionUID = 1L;
	private String decorated_content;
	
	public TextDecorator(String content) {
		super(content);
		decorated_content = content;
		
		//#if TextColor && CLI
		decorated_content = new TextColor().decorateText(decorated_content);
		//#endif
		
		//#if Swap2Letters
		decorated_content = new Swap2Letters().encrypt(decorated_content);
		//#endif
		
		//#if Rot13
//@		decorated_content = new ROT13().encrypt(decorated_content);
		//#endif
	}
	
	@Override 
	public String getContent() {
		String sending_content = decorated_content;		
		
		//#if Swap2Letters
		sending_content = new Swap2Letters().decrypt(sending_content);
		//#endif
		
		//#if Rot13		
//@		sending_content = new ROT13().decrypt(sending_content);
		//#endif
		
		return sending_content;
	}
	

}
//#endif
