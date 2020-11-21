package beans;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Oglas;

@Singleton
@Startup
public class ReviewNumberBean implements ReviewNumberBeanRemote {

    private HashMap<Integer, Integer> mapReviews;
    private Integer todayResponds;
    
    private @PersistenceContext EntityManager entityManager;
    private @Resource TimerService timerService;
    private @Resource Timer timer;
    
    public ReviewNumberBean() {
        
    	this.mapReviews = new HashMap<>();
    	this.todayResponds = 0;
    }
    
    @PostConstruct
    public void startTimer() {
    	var tc = new TimerConfig();
    	tc.setPersistent(false);
    	timer = timerService.createIntervalTimer(0, 24*60*60*1000, tc);
    }
    
    @Override
    public HashMap<Integer, Integer> getMapReviews(){
    	return mapReviews;
    }
    
    @Override
    public Integer getTodayResponds(){
    	return todayResponds;
    }

	@Override
	public void updateMap(Integer key, Integer value) {
		getMapReviews().put(key, value);
	}
	
	@Timeout
	public void timeout(Timer timer) {
		System.err.println("Todays respond to add -> " + todayResponds);
		this.todayResponds = 0;
	}
	
	@Schedule(second = "*", persistent = false, minute="*/15", hour="*")
	public void updateDatabase() {

		System.err.println("Updating database!");
		
		getMapReviews().entrySet()
		               .stream()
		               .forEach(this::mapFromEntrySet);
		
	}
	
	private void mapFromEntrySet(Entry<Integer, Integer> obj) {
		
		var key = obj.getKey();
		
		var add = entityManager.createQuery("select o from Oglas o where o.idOglas like :key", Oglas.class)
				               .setParameter("key", key)
				               .getSingleResult();
		
		var reviews = add.getBrojPregleda() + obj.getValue();
		
		entityManager.createQuery("UPDATE Oglas o SET o.brojPregleda = :reviews where o.idOglas like :idAdd")
		             .setParameter("reviews", reviews)
		             .setParameter("idAdd", add.getIdOglas())
		             .executeUpdate();
		
	}

	@Override
	public void setRespond() {
		this.todayResponds++;
	}
}
