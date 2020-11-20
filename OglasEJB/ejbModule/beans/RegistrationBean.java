package beans;

import interfaces.RegistrationRemote;
import model.Oglaskorisnik;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class RegistrationBean implements RegistrationRemote {
	
	private @PersistenceContext EntityManager entityManager;
    public RegistrationBean() { }

	@Override
	public boolean register(String nickname, String username, String password) {
		
		try {
			
			var korisnik = new Oglaskorisnik();
			korisnik.setNickname(nickname);
			korisnik.setUsername(username);
			korisnik.setPassword(password);
			
			entityManager.persist(korisnik);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
