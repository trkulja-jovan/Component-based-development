package interfaces;

import javax.ejb.Remote;

@Remote
public interface RegistrationRemote {
	
	public boolean register(String nickname, String username, String password);
}
