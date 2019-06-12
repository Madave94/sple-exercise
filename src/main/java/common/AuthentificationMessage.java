package common;

public class AuthentificationMessage extends MessageProtocol{
		
	public AuthentificationMessage(boolean access) {
		super( access );
	}
	
	@Override
	public Boolean getContent() {
		return (boolean) content;
	}

}