package common;

public class AuthentificationMessage extends RegistrationProtocol{
		
	public AuthentificationMessage(boolean access) {
		super( access );
	}
	
	@Override
	public Boolean getContent() {
		return (boolean) content;
	}
}