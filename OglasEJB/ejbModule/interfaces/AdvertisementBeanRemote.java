package interfaces;

import java.util.List;

import javax.ejb.Remote;

import model.Oglas;
import model.Oglaskorisnik;

@Remote
public interface AdvertisementBeanRemote {
	
	public Oglaskorisnik login(String username, String password);
	public List<Oglas> findAdvertisements(String keyword);
	public boolean addAdvertisement(String text);
	public boolean respond(int idAdd);
}
