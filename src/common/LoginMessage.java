package common;

public class LoginMessage extends RegistrationProtocol {
	
	public LoginMessage(String username ) {
		super( username );
		String[] tokens = this.getContent().split(" ");
		if (tokens.length == 2) this.content = tokens[1].trim(); 
		else this.content = tokens[0].trim();
	}
	
	@Override
	public String getContent() {
		return getUsername();
	}
	
	public String getUsername() {
		return (String) this.content;
	}
}
