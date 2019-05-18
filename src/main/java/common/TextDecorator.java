//#if TextColor || Rot13 || Swap2Letters
//@package common;
//@
//@public class TextDecorator extends TextMessage{
//@
//@	private static final long serialVersionUID = 1L;
//@	private String decorated_content;
//@	
//@	public TextDecorator(String content) {
//@		super(content);
//@		decorated_content = content;
//@		
//@		//if 
//@		decorated_content = new TextColor().decorateText(decorated_content);
//@		
//@		// Apply Encryption
//@		decorated_content = new Swap2Letters().encrypt(decorated_content);
//@	}
//@	
//@	@Override 
//@	public String getContent() {
//@		// Apply Decryption
//@		String sending_content = decorated_content;
//@		
//@		// Apply Decryption
//@		sending_content = new Swap2Letters().decrypt(sending_content);
//@		
//@		return sending_content;
//@	}
//@	
//@
//@}
//#endif
