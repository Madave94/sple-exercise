package common;

public class LoginMessage extends RegistrationProtocol {
	
	public LoginMessage(String username ) {
		super( username );
		String[] tokens = this.getContent().split(" ");
		this.content = tokens[1].trim();
	}
	
	@Override
	public String getContent() {
		return getUsername();
	}
	
	public String getUsername() {
		return (String) this.content;
	}
}
