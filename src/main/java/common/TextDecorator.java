package common;

import plugin.EncryptionPlugin;
import plugin.Plugin;

public class TextDecorator extends TextMessage{

	private static final long serialVersionUID = 1L;
	private String decorated_content;
	
	public TextDecorator(String content) {
		super(content);
		decorated_content = content;
		
		decorated_content = new TextColor().decorateText(decorated_content);
		
		if (Plugin.getInstance().getSpamFilterPlugin() != null)
		decorated_content =  Plugin.getInstance()
								.getSpamFilterPlugin()
								.filter(decorated_content);		
		
		if (Plugin.getInstance().getEncryptionPlugin() != null)
			decorated_content = Plugin.getInstance()
								.getEncryptionPlugin()
								.encrypt(decorated_content);
	}
	
	@Override 
	public String getContent() {
		String sending_content = decorated_content;		
		
		if (Plugin.getInstance().getEncryptionPlugin() != null)
			sending_content = Plugin.getInstance()
								.getEncryptionPlugin()
								.decrypt(decorated_content);
		
		return sending_content;
	}
	

}
