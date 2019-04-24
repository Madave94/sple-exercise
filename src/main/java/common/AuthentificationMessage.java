package common;

import java.io.Serializable;

public class AuthentificationMessage implements Serializable{

	private static final long serialVersionUID = -5850676626808871111L;
	
	private String password;
	
	public AuthentificationMessage(String password) {
		super();
		this.password = password;
	}
	
	public String getContent() {
		return password;
	}

}
