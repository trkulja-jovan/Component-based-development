package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interceptors.Interceptor;
import interceptors.StatefulInterceptor;
import interfaces.AdvertisementBeanRemote;
import model.Oglas;
import model.Oglaskorisnik;
import model.Oglasprijava;

@Stateful
@LocalBean
@Interceptors(StatefulInterceptor.class)
public class AdvertisementBean implements AdvertisementBeanRemote {

    private @PersistenceContext EntityManager entityManager;
    private Oglaskorisnik loggedUser;
    
    public AdvertisementBean() {
    	
    }

	@Override
	public Oglaskorisnik login(String username, String password) {
		
		try {
			
			loggedUser = entityManager.createQuery("select o from Oglaskorisnik o where o.username like :username and o.password like :password", Oglaskorisnik.class)
					            	  .setParameter("username", username)
					                  .setParameter("password", password)
					                  .getSingleResult();
			
			return loggedUser;
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Oglas> findAdvertisements(String keyword) {
		
		try {
			
			return entityManager.createQuery("select o from Oglas o where o.text like :keyword", Oglas.class)
					            .setParameter("keyword", keyword)
					            .getResultList();
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean addAdvertisement(String text) {
		
		try {
			
			var addv = new Oglas();
			addv.setBrojPregleda(0);
			addv.setText(text);
			addv.setOglaskorisnik(loggedUser);
			
			entityManager.persist(addv);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	@Interceptors({Interceptor.class})
	public boolean respond(int idAdd) {
		try {
			
			var advertisment = entityManager.createQuery("select o from Oglas o where o.idOglas like :idAdd", Oglas.class)
					                        .setParameter("idAdd", idAdd)
					                        .getSingleResult();
			
			var text = loggedUser.getNickname() + ", want to buy " + advertisment.getText();
			
			var respondObj = new Oglasprijava();
			respondObj.setOglaskorisnik(loggedUser);
			respondObj.setText(text);
			respondObj.setOglas(advertisment);
			
			entityManager.persist(respondObj);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}

}
