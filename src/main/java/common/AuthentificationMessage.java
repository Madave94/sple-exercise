//#if Authentification
package common;

import java.io.Serializable;

public class AuthentificationMessage implements Serializable{

	private static final long serialVersionUID = -5850676626808871111L;
	
	private boolean access;
	
	public AuthentificationMessage(boolean access) {
		super();
		this.access = access;
	}
	
	public boolean getContent() {
		return access;
	}

}
//#endif 
